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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.entities.Vote;
import ch.hearc.springwater.models.repositories.BoissonsRepository;
import ch.hearc.springwater.models.repositories.VoteRepository;
import ch.hearc.springwater.security.Utilisateur;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BoissonRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BoissonsRepository boissonRepository;

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	public void givenBoisson_whenPersistBoisson_theBoissonIsPersisted() {
		Boisson boisson = new Boisson();
		boisson.setNom("SpringWater");
		boisson.setDescription("Best drink ever");
		boisson.setFileURL("");

		entityManager.persist(boisson);
		entityManager.flush();

		Optional<Boisson> boissonRecherche = boissonRepository.findById(boisson.getId());

		assertTrue(boissonRecherche.isPresent());
		assertThat(boissonRecherche.get()).isNotNull();
		assertTrue(boisson.getId().equals(boissonRecherche.get().getId()));
	}

	@Test
	public void givenBoisson_whenPersistBoisson_theBoissonHasRightName() {
		String name = "SpringWater made with love";
		Boisson boisson = new Boisson();
		boisson.setNom(name);
		boisson.setDescription("Best drink ever looks like");
		boisson.setFileURL("");

		entityManager.persist(boisson);
		entityManager.flush();

		Optional<Boisson> boissonRecherche = boissonRepository.findById(boisson.getId());

		assertTrue(boissonRecherche.isPresent());
		assertThat(boissonRecherche.get()).isNotNull();
		assertTrue(name.equals(boissonRecherche.get().getNom()));
	}

	@Test
	public void givenBoisson_whenPersistBoisson_theBoissonHasRightDescription() {
		String description = "Best drink ever for sure";
		Boisson boisson = new Boisson();
		boisson.setNom("Soda");
		boisson.setDescription(description);
		boisson.setFileURL("");

		entityManager.persist(boisson);
		entityManager.flush();

		Optional<Boisson> boissonRecherche = boissonRepository.findById(boisson.getId());

		assertTrue(boissonRecherche.isPresent());
		assertThat(boissonRecherche.get()).isNotNull();
		assertTrue(description.equals(boissonRecherche.get().getDescription()));
	}

	@Test
	public void givenBoisson_whenPersistBoisson_theBoissonHasRightCategories() {
		String description = "Best drink ever for sure";
		Boisson boisson = new Boisson();
		boisson.setNom("Soda");
		boisson.setDescription(description);

		List<Object> listCategoriesNameLinked = Arrays.asList(new String[] { "vodka", "soda", "Test", "Rongob" });
		List<Object> listCategoriesNameNotLinked = Arrays.asList(new String[] { "Fromage", "Raddis", "Youpoulala" });

		Set<Categorie> setCategories = new HashSet<>();
		for (Object name : listCategoriesNameLinked) {
			Categorie categorie = new Categorie();
			categorie.setNom((String) name);
			entityManager.persist(categorie);
			entityManager.flush();
			setCategories.add(categorie);
		}

		for (Object name : listCategoriesNameNotLinked) {
			Categorie categorie = new Categorie();
			categorie.setNom((String) name);
			entityManager.persist(categorie);
			entityManager.flush();
		}

		boisson.setCategories(setCategories);
		boisson.setFileURL("");

		entityManager.persist(boisson);
		entityManager.flush();

		Optional<Boisson> boissonRecherche = boissonRepository.findById(boisson.getId());

		assertTrue(boissonRecherche.isPresent());
		assertThat(boissonRecherche.get()).isNotNull();
		assertTrue(setCategories.equals(boissonRecherche.get().getCategories()));
	}

	@Test
	public void givenBoisson_whenPersistBoisson_theBoissonHasRightVotes() {
		final int COUNT = 2;
		final int[] SCORE = new int[] { 1, -1 };
		Utilisateur[] utilisateurs = new Utilisateur[COUNT];
		Boisson[] boissons = new Boisson[COUNT];

		for (int i = 0; i < COUNT; i++) {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNomUtilisateur("user" + i);
			utilisateur.setMotDePasse(bCryptPasswordEncoder.encode("password"));
			utilisateurs[i] = utilisateur;

			entityManager.persist(utilisateur);
			entityManager.flush();
		}

		for (int i = 0; i < COUNT; i++) {
			Boisson boisson = new Boisson();
			boisson.setNom("Drink" + i);
			boisson.setDescription("Best drink ever for sure");
			boisson.setFileURL("");
			boissons[i] = boisson;

			entityManager.persist(boisson);
			entityManager.flush();
		}

		for (int i = 0; i < COUNT; i++) {
			Utilisateur utilisateur = utilisateurs[i];
			Vote vote = new Vote();
			vote.setUser(utilisateur);
			vote.setBoisson(boissons[i]);
			vote.setScore(SCORE[i]);
			System.out.println(vote.getScore());

			entityManager.persist(vote);
			entityManager.flush();
		}

		for (int i = 0; i < COUNT; i++) {
			Boisson boisson = boissons[i];
			int score = voteRepository.getScore(boisson.getId());
			//Warning -> ne pas utiliser boisson.getScore() car cette valeur est en cache
			 assertTrue(SCORE[i] == score);
		}
	}

}
