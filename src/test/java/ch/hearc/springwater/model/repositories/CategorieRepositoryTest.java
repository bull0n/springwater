package ch.hearc.springwater.model.repositories;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Vote;
import ch.hearc.springwater.security.Utilisateur;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategorieRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final int SCORE = 1;

	@Test
	@WithMockUser(username = "admin", roles = { "ROLE_ADMIN" })
	public void givenCategorie_whenPersistCategorie_theCategorieHasBoissonList() {
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
