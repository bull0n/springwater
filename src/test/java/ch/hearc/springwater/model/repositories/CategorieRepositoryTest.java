package ch.hearc.springwater.model.repositories;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.repositories.CategoriesRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategorieRepositoryTest {

	@Autowired
	CategoriesRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void givenCategorie_whenPersistCategorie_theCategorieHasEmptyBoissonList() {
		Categorie categorie = new Categorie();
		categorie.setNom("Categorie one");

		Categorie persistedCategorie = entityManager.persist(categorie);
		entityManager.flush();

		assertTrue(persistedCategorie.getNbBoisson() == 0);
	}
}
