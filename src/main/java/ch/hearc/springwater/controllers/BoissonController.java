package ch.hearc.springwater.controllers;

import java.util.Map;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.hearc.springwater.exceptions.ResourceNotFoundException;
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
		return "boisson/see-boissons";
	}
	
	@GetMapping(value="/{id}")
	public String getBoisson(@PathVariable("id") long id, Map<String, Object> model)
	{		
		Boisson boisson = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		model.put("boisson", boisson);
		
		return "boisson/see-detail";
	}
	
	@GetMapping(value="/add")
	public String addBoissonMap(Map<String, Object> model)
	{
		model.put("boisson", new Boisson());
		return "boisson/form";
	}
	
	@PostMapping(value="/save")
	public String save(Boisson boisson)
	{
		repository.save(boisson);
		
		return "redirect:/boisson/";
	}
	
	@GetMapping(value="/edit/{id}")
	public String edit(@PathVariable("id") long id, Map<String, Object> model)
	{
		Boisson boisson = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		model.put("boisson", boisson);

		return "boisson/form";
	}
	
	@PutMapping(value="/update")
	public String update(Boisson boisson)
	{
		repository.save(boisson);
		
		return "boisson/form";
	}
}
