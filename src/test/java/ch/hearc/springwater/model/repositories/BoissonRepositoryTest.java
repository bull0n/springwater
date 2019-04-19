package ch.hearc.springwater.model.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.springwater.models.entities.Boisson;
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
		assertTrue(boisson.getId().equals(boissonRecherche.get().getId()));
		assertThat(boissonRecherche.get()).isNotNull();
	}
	
	//TODO:

}
