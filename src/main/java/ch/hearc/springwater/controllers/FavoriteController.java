package ch.hearc.springwater.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.springwater.exceptions.ForbiddenException;
import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.security.UtilisateurRepository;
import ch.hearc.springwater.service.impl.UtilisateurDetailServiceImpl;

@RestController
@RequestMapping(value = "/favorite")
public class FavoriteController
{
	@Autowired
	private UtilisateurDetailServiceImpl utilisateurService;
	@Autowired 
	private UtilisateurRepository utilisateurRepository;
	
	@Secured("ROLE_USER")
	@PostMapping(value = "/add/{boissonId}")
	public boolean addFavoriteBoisson(@PathVariable("boissonId") Boisson boisson, Map<String, Object> model)
	{
		Utilisateur user = utilisateurService.loadCurrentUser();
		
		if(user != null)
		{
			user.getBoissonsFavorite().add(boisson);
			utilisateurRepository.save(user);
			
			return true;
		}
		else
		{
			throw new ForbiddenException();
		}
	}
	
	@Secured("ROLE_USER")
	@DeleteMapping(value = "/remove/{boissonId}")
	public boolean removeFavoriteBoisson(@PathVariable("boissonId") Boisson boisson, Map<String, Object> model)
	{
		Utilisateur user = utilisateurService.loadCurrentUser();
		
		if(user != null)
		{
			user.getBoissonsFavorite().remove(boisson);
			utilisateurRepository.save(user);
			
			return true;
		}
		else
		{
			throw new ForbiddenException();
		}
	}
}
