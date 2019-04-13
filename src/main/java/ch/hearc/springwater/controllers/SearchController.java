package ch.hearc.springwater.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.repositories.BoissonsRepository;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	BoissonsRepository repository;

	@GetMapping(value = "/")
	public String search(@RequestParam(value = "search", required = false) String q, Map<String, Object> model) {
		List<Boisson> searchResults = repository.findBoissonSimple(q);
		model.put("boissons", searchResults);
		return "boisson/see-boissons";
	}
}
