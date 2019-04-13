package ch.hearc.springwater.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.springwater.models.entities.Boisson;

@RestController
@RequestMapping(value = "/favorite")
public class FavoriteController
{
	@PostMapping(value = "/add")
	public boolean addFavoriteBoisson(Boisson boisson, Map<String, Object> model)
	{
		System.out.println(boisson);
		return true;
	}
	
	@DeleteMapping(value = "/remove")
	public boolean removeFavoriteBoisson(Boisson boisson, Map<String, Object> model)
	{
		System.out.println(boisson);
		return true;
	}
}
