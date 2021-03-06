package ch.hearc.springwater.model.repositories;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Vote;
import ch.hearc.springwater.security.Utilisateur;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VoteRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

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
		Vote persistedVote = entityManager.persist(vote);

		assertTrue(persistedVote.getBoisson().getNom().equals(boisson.getNom()));
	}
	
	@Test
	public void givenVote_whenSetId_theVoteHasRightId() {
		Vote vote = new Vote();
		vote.setId(1L);
		assertTrue(vote.getId().equals(1L));
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
		Vote persistedVote = entityManager.persist(vote);

		assertTrue(persistedVote.getScore() == SCORE);
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
		Vote persistedVote = entityManager.persist(vote);

		assertTrue(persistedVote.getUser().getNomUtilisateur().equals(utilisateur.getNomUtilisateur()));
	}

}
