package ch.hearc.springwater.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.springwater.exceptions.ResourceNotFoundException;
import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Vote;
import ch.hearc.springwater.models.repositories.BoissonsRepository;
import ch.hearc.springwater.models.repositories.VoteRepository;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.security.UtilisateurRepository;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {
	private static final int SCORE_UP = 1;
	private static final int SCORE_DOWN = -1;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	BoissonsRepository boissonRepository;

	@Autowired
	VoteRepository voteRepository;

	@PostMapping(value = "/upvote/{id}")
	public Map<String, Integer> upVote(@PathVariable Long id, Model model) {
		return this.vote(id, SCORE_UP);
	}

	@PostMapping(value = "/downvote/{id}")
	public Map<String, Integer> downVote(@PathVariable Long id, Model model) {
		return this.vote(id, SCORE_DOWN);
	}

	public Map<String, Integer> vote(Long id, int score) {
		Authentication authentification = SecurityContextHolder.getContext().getAuthentication();
		Boisson boisson = boissonRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
		Map<String, Integer> map = new HashMap<>();

		if (authentification.isAuthenticated()) {
			String nom = authentification.getName();
			Utilisateur utilisateurCourant = utilisateurRepository.findByNomUtilisateur(nom);

			List<Vote> listeVotes = utilisateurCourant.getVotes();
			Vote vote = this.getVoteFromList(id, listeVotes);

			if (vote == null) {
				Vote nouveauVote = new Vote();
				nouveauVote.setScore(score);
				nouveauVote.setBoisson(boisson);
				nouveauVote.setUser(utilisateurCourant);
				listeVotes.add(nouveauVote);
				utilisateurRepository.save(utilisateurCourant);

				map.put("score", score);
			} else if (vote.getScore() != score) {
				vote.setScore(score);
				voteRepository.save(vote);

				map.put("score", score);
			}
		}

		map.put("total_score", boisson.getScore());

		return map;
	}

	public Vote getVoteFromList(Long id, List<Vote> listeVotes) {
		for (Vote vote : listeVotes) {
			if (vote.getBoisson().getId().equals(id)) {
				return vote;
			}
		}

		return null;
	}
}
