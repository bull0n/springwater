package ch.hearc.springwater.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.hearc.springwater.exceptions.FileException;
import ch.hearc.springwater.exceptions.ResourceNotFoundException;
import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Vote;
import ch.hearc.springwater.models.repositories.BoissonsRepository;
import ch.hearc.springwater.models.repositories.CategoriesRepository;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.service.impl.FileService;
import ch.hearc.springwater.service.impl.UtilisateurDetailServiceImpl;

@Controller
@RequestMapping(value = "/boisson")
public class BoissonController {

	@Autowired
	BoissonsRepository repository;

	@Autowired
	CategoriesRepository categoriesRepository;

	@Autowired
	private FileService fileStorageService;
	
	@Autowired
	private UtilisateurDetailServiceImpl utilisateurService;

	private static final int BOISSONS_PAR_PAGE = 10;
	private static final String BOISSON = "boisson";
	private static final String CATEGORIES = "categories";
	private static final String REDIRECT_BOISSON = "redirect:/boisson/";

	@GetMapping(value = "/")
	public String getBoissons(Map<String, Object> model) {
		this.getBoissonsPageable(1, model);
		this.getColors(model);
		model.put("user", utilisateurService.loadCurrentUser());
		model.put(CATEGORIES, categoriesRepository.findAll());
		return "boisson/see-boissons";
	}

	@GetMapping(value = "/page/{pageNum}")
	public String getBoissons(@PathVariable("pageNum") int pageNum, Map<String, Object> model) {
		this.getBoissonsPageable(pageNum, model);
		this.getColors(model);
		model.put("user", utilisateurService.loadCurrentUser());
		model.put(CATEGORIES, categoriesRepository.findAll());
		return "boisson/see-boissons";
	}

	@GetMapping(value = "/{id}")
	public String getBoisson(@PathVariable("id") long id, Map<String, Object> model) {
		Boisson boisson = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
		model.put(BOISSON, boisson);

		return "boisson/see-detail";
	}

	@Secured("ROLE_USER")
	@GetMapping(value = "/add")
	public String addBoisson(Map<String, Object> model) {
		model.put(BOISSON, new Boisson());
		model.put(CATEGORIES, categoriesRepository.findAll());

		return "boisson/boisson-add";
	}
	
	@Secured("ROLE_USER")
	@PutMapping(value = "/update")
	public String update(Boisson boisson) throws FileException {
		saveImageBoisson(boisson);
		repository.save(boisson);

		return REDIRECT_BOISSON;
	}

	@Secured("ROLE_USER")
	@PostMapping(value = "/save")
	public String save(Boisson boisson) throws FileException {
		this.saveImageBoisson(boisson);
		repository.save(boisson);

		return REDIRECT_BOISSON;
	}

	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") long id, Map<String, Object> model) {
		Boisson boisson = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
		model.put(BOISSON, boisson);
		model.put(CATEGORIES, categoriesRepository.findAll());

		return "boisson/boisson-edit";
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") long id, Map<String, Object> model) {
		repository.deleteById(id);
		// TODO: Delete image file
		return REDIRECT_BOISSON;
	}

	private void getColors(Map<String, Object> model) {
		List<String> upVoteList = new ArrayList<>();
		List<String> downVoteList = new ArrayList<>();
		Utilisateur utilisateur = utilisateurService.loadCurrentUser();

		if (utilisateur != null) {
			List<Vote> listeVotes = utilisateur.getVotes();

			for (Vote vote : listeVotes) {
				int score = vote.getScore();

				if (score == 1) {
					upVoteList.add(vote.getBoisson().getId() + "");
				} else {
					downVoteList.add(vote.getBoisson().getId() + "");
				}
			}

			model.put("upVoteList", upVoteList);
			model.put("downVoteList", downVoteList);
		}
	}

	private void getBoissonsPageable(int pageNum, Map<String, Object> model) {
		Pageable pageable = PageRequest.of(pageNum - 1, BOISSONS_PAR_PAGE);
		Page<Boisson> page = repository.findAll(pageable);

		List<Integer> listPages = new ArrayList<>();
		int firstPage = pageNum - 5;
		for (int i = firstPage > 0 ? firstPage : 1; i <= (pageNum + 5) && i <= page.getTotalPages(); i++) {
			listPages.add(i);
		}

		model.put("listPages", listPages);
		model.put("pageNum", pageNum);
		model.put("boissons", page);
	}

	private void saveImageBoisson(Boisson boisson) throws FileException {
		boisson.setFileURL(fileStorageService.storeFile(boisson.getFile()));

		boisson.setOwner(utilisateurService.loadCurrentUser());
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/")
				.path(boisson.getFileURL()).toUriString();
		boisson.setFileURL(fileDownloadUri);
	}
}
