package ch.hearc.springwater.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.view.ThymeleafView;

import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.repositories.CategoriesRepository;

@Controller
@RequestMapping(value = "/categorie")
public class CategorieController
{
	@Autowired
	CategoriesRepository repository;
	
	@GetMapping(value="/")
	public String getCategories(Map<String, Object> model)
	{		
		model.put("categories", repository.findAll());
		return "categories/see-categories";
	}
	
	@GetMapping(value="/add")
	public String addCategorieMap(Map<String, Object> model)
	{
		model.put("categorie", new Categorie());
		return "categories/add-categorie";
	}
	
	@PostMapping(value="/save")
	public String save(Categorie categorie)
	{
		repository.save(categorie);
		
		return "redirect:/categorie/";
	}
}
