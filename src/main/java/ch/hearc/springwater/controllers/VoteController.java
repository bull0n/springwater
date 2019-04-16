package ch.hearc.springwater.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.springwater.exceptions.ResourceNotFoundException;
import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Vote;
import ch.hearc.springwater.models.repositories.BoissonsRepository;
import ch.hearc.springwater.models.repositories.VoteRepository;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.security.UtilisateurRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {
	private int SCORE_UP = 1;
	private int SCORE_DOWN = -1;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	BoissonsRepository boissonRepository;

	@Autowired
	VoteRepository voteRepository;

	@PostMapping(value = "/upvote/{id}")
	public Map<String, String> UpVote(@PathVariable Long id, Model model) {
		Integer score = this.vote(id, model, SCORE_UP);

		return Collections.singletonMap("score", score.toString());
	}

	@PostMapping(value = "/downvote/{id}")
	public Map<String, String> DownVote(@PathVariable Long id, Model model) {
		Integer score = this.vote(id, model, SCORE_DOWN);

		return Collections.singletonMap("score", score.toString());
	}

	public Integer vote(Long id, Model model, int score) {
		Authentication authentification = SecurityContextHolder.getContext().getAuthentication();
		Boisson boisson = boissonRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

		if (authentification.isAuthenticated()) {
			String nom = authentification.getName();
			Utilisateur utilisateurCourant = utilisateurRepository.findByNomUtilisateur(nom);

			List<Vote> listeVotes = utilisateurCourant.getVotes();
			Vote vote = this.getVoteFromList(id, listeVotes);
			
			if(vote == null)
			{
				Vote nouveauVote = new Vote();
				nouveauVote.setScore(score);
				nouveauVote.setBoisson(boisson);
				nouveauVote.setUser(utilisateurCourant);
				listeVotes.add(nouveauVote);
				utilisateurRepository.save(utilisateurCourant);
			}
			else if(vote.getScore() != score)
			{
				vote.setScore(score);
				voteRepository.save(vote);
			}
		}
		
		return boisson.getScore();
	}

	public Vote getVoteFromList(Long id, List<Vote> listeVotes) {
		for (Vote vote : listeVotes) {
			if(vote.getBoisson().getId() == id) {
				return vote;
			}
		}
		
		return null;
	}
}
