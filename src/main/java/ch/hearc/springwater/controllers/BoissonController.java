package ch.hearc.springwater.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.repositories.BoissonsRepository;


@Controller
@RequestMapping(value = "/boisson")
public class BoissonController
{
	@Autowired
	BoissonsRepository repository;
	
	@GetMapping(value="/")
	public String getBoissons(Map<String, Object> model)
	{		
		model.put("boissons", repository.findAll());
		return "see-boissons";
	}
	
	@GetMapping(value="/add-boisson")
	public String addBoissonMap(Map<String, Object> model)
	{
		model.put("boisson", new Boisson());
		return "add-boisson";
	}
	
	@PostMapping(value="/save")
	public String save(Boisson boisson)
	{
		repository.save(boisson);
		
		return "redirect:/";
	}
}
