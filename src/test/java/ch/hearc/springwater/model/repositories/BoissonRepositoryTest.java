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
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.repositories.BoissonsRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BoissonRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BoissonsRepository boissonRepository;

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
		
		List<Object> listCategoriesNameLinked = Arrays.asList(new String[]{"vodka", "soda", "Test", "Rongob"});
		List<Object> listCategoriesNameNotLinked = Arrays.asList(new String[]{"Fromage", "Raddis", "Youpoulala"});
		
		Set<Categorie> setCategories = new HashSet<>();
		for(Object name : listCategoriesNameLinked) {
			Categorie categorie = new Categorie();
			categorie.setNom((String)name);
			entityManager.persist(categorie);
			entityManager.flush();
			setCategories.add(categorie);
		}

		for(Object name : listCategoriesNameNotLinked) {
			Categorie categorie = new Categorie();
			categorie.setNom((String)name);
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
		//TODO: 
	}
	
}
