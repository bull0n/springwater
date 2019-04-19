package ch.hearc.springwater.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.repositories.BoissonsRepository;
import ch.hearc.springwater.models.repositories.CategoriesRepository;

@Controller
@RequestMapping(value = "/recherche")
public class SearchController {

	private static final long NO_CATEGORIE = -1L;

	@Autowired
	BoissonsRepository repository;

	@Autowired
	CategoriesRepository categoriesRepository;

	@GetMapping(value = "/rapide")
	public String rechercheRapide(@RequestParam(value = "boisson", required = false) String q,
			Map<String, Object> model) {
		List<Boisson> searchResults = repository.findBoisson(q);
		model.put("boissons", searchResults);
		model.put("categories", categoriesRepository.findAll());
		return "boisson/see-boissons";
	}

	@GetMapping(value = "/avancee")
	public String rechercheAvancee(@RequestParam(value = "boisson", required = false) String q,
			@RequestParam(value = "categoriesId", required = false) List<String> categories,
			@RequestParam(value = "order", required = false) String orderString, Map<String, Object> model) {

		// Validate input
		categories = categories == null ? new ArrayList<>() : categories;
		q = q == null || q.isEmpty() ? "%" : q;
		int order = 0;
		try {
			order = orderString == null ? 0 : Integer.parseInt(orderString);
		} catch (NumberFormatException e) {
			order = 0;
		}
		List<Long> listCategoriesId = categories.parallelStream().mapToLong(c -> Long.parseLong(c)).boxed()
				.collect(Collectors.toList());

		List<Boisson> searchResults = repository.findBoisson(q);

		Comparator<Boisson> nameASC = (b1, b2) -> b1.getNom().compareTo(b2.getNom());
		Comparator<Boisson> nameDSC = (b1, b2) -> b2.getNom().compareTo(b1.getNom());

		switch (order) {
		case 0: // Best match
			break;
		case 1: // Name ASC
			searchResults.sort(nameASC);
			break;
		case 2: // Name DSC
			searchResults.sort(nameDSC);
			break;
		}

		Predicate<Boisson> filterCategorieOnly = b -> b.getCategories().stream()
				.anyMatch(c -> listCategoriesId.contains(c.getId()));
		Predicate<Boisson> filterCategorieOrNothing = b -> b.getCategories().stream()
				.anyMatch(c -> listCategoriesId.contains(c.getId())) || b.getCategories().size() == 0;

		Predicate<Boisson> filterCategorie;
		
		if (listCategoriesId.contains(NO_CATEGORIE)) {
			filterCategorie = filterCategorieOrNothing;
		} else {
			filterCategorie = filterCategorieOnly;
		}

		searchResults = searchResults.stream().filter(filterCategorie).collect(Collectors.toList());

		model.put("research", q);
		model.put("order", Integer.parseInt(orderString));
		model.put("categoriesId", listCategoriesId);
		
		model.put("boissons", searchResults);
		model.put("categories", categoriesRepository.findAll());
		
		
		return "boisson/see-boissons";
	}

}
