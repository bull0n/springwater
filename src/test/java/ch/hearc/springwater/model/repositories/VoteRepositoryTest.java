package ch.hearc.springwater.model.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.entities.Vote;
import ch.hearc.springwater.models.repositories.BoissonsRepository;
import ch.hearc.springwater.models.repositories.VoteRepository;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.security.UtilisateurRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VoteRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BoissonsRepository boissonRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final int SCORE = 1;
	
	@Test
	public void givenVote_whenPersistVote_theVoteHasRightBoisson() {
		Boisson boisson = new Boisson();
		boisson.setNom("Soda");
		boisson.setDescription("");
		boisson.setFileURL("");
		entityManager.persist(boisson);
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNomUtilisateur("Jean Emarre");
		utilisateur.setMotDePasse(bCryptPasswordEncoder.encode("password"));
		entityManager.persist(utilisateur);
		
		Vote vote = new Vote();
		vote.setScore(SCORE);
		vote.setBoisson(boisson);
		vote.setUser(utilisateur);
		entityManager.persist(vote);
		
		Iterable<Vote> votes = voteRepository.findAll();

		for(Vote currentVote : votes)
		{
			assertTrue(currentVote.getBoisson().getNom().equals(boisson.getNom()));
		}
	}
	
	@Test
	public void givenVote_whenPersistVote_theVoteHasRightScore() {
		Boisson boisson = new Boisson();
		boisson.setNom("Soda");
		boisson.setDescription("");
		boisson.setFileURL("");
		entityManager.persist(boisson);
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNomUtilisateur("Jean Emarre");
		utilisateur.setMotDePasse(bCryptPasswordEncoder.encode("password"));
		entityManager.persist(utilisateur);
		
		Vote vote = new Vote();
		vote.setScore(SCORE);
		vote.setBoisson(boisson);
		vote.setUser(utilisateur);
		entityManager.persist(vote);
		
		Iterable<Vote> votes = voteRepository.findAll();

		for(Vote currentVote : votes)
		{
			assertTrue(currentVote.getScore() == SCORE);
		}
	}
	
	@Test
	public void givenVote_whenPersistVote_theVoteHasRightUtilisateur() {
		Boisson boisson = new Boisson();
		boisson.setNom("Soda");
		boisson.setDescription("");
		boisson.setFileURL("");
		entityManager.persist(boisson);
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNomUtilisateur("Jean Emarre");
		utilisateur.setMotDePasse(bCryptPasswordEncoder.encode("password"));
		entityManager.persist(utilisateur);
		
		Vote vote = new Vote();
		vote.setScore(SCORE);
		vote.setBoisson(boisson);
		vote.setUser(utilisateur);
		entityManager.persist(vote);
		
		Iterable<Vote> votes = voteRepository.findAll();

		for(Vote currentVote : votes)
		{
			assertTrue(currentVote.getUser().getNomUtilisateur().equals(utilisateur.getNomUtilisateur()));
		}
	}
	
}
