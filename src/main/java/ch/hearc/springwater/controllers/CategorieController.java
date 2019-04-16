package ch.hearc.springwater.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.hearc.springwater.exceptions.ResourceNotFoundException;
import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.repositories.CategoriesRepository;

@Controller
@RequestMapping(value = "/categorie")
public class CategorieController {
	@Autowired
	CategoriesRepository repository;

	private final static String CATEGORIE = "categorie";
	private final static String REDIRECT_CATEGORIE = "redirect:/categorie/";
	
	@GetMapping(value = "/")
	public String getCategories(Map<String, Object> model) {
		model.put("categories", repository.findAll());
		return "categories/see-categories";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/add")
	public String addCategorieMap(Map<String, Object> model) {
		model.put(CATEGORIE, new Categorie());
		return "categories/categorie-add";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping(value = "/edit/{id}")
	public String getBoisson(@PathVariable("id") long id, Map<String, Object> model) {
		Categorie categorie = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
		model.put(CATEGORIE, categorie);

		return "categories/categorie-edit";
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/remove/{id}")
    public String deleteBuyer(@PathVariable Long id){
		repository.deleteById(id);

        return REDIRECT_CATEGORIE;
    }

	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/save")
	public String save(Categorie categorie) {
		repository.save(categorie);

		return REDIRECT_CATEGORIE;
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping(value = "/update")
	public String update(Categorie categorie) {
		repository.save(categorie);

		return REDIRECT_CATEGORIE;
	}
}
