package ch.hearc.springwater.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.repositories.BoissonsRepository;
import ch.hearc.springwater.models.repositories.CategoriesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	BoissonsRepository boissonsRepository;

	@MockBean
	CategoriesRepository categoriesRepository;

	@Before
	public void setUp() {
		Categorie c1 = new Categorie();
		c1.setId(1L);
		c1.setNom("Cat 1");
		Categorie c2 = new Categorie();
		c2.setId(1L);
		c2.setNom("Cat 2");

		List<Categorie> listCategorie = new ArrayList<>();
		listCategorie.add(c1);
		listCategorie.add(c2);

		Mockito.when(categoriesRepository.findAll()).thenReturn(listCategorie);

		Boisson b1 = new Boisson();
		b1.setId(1L);
		b1.setNom("Water");
		b1.setDescription("Best drink ever");
		b1.setCategories(new HashSet<>());
		
		Boisson b2 = new Boisson();
		b2.setId(2L);
		b2.setNom("Water");
		b2.setDescription("Best drink ever");
		Set<Categorie> categories = new HashSet<>();
		categories.add(c1);
		b2.setCategories(categories);
		
		List<Boisson> listBoisson = new ArrayList<>();
		listBoisson.add(b1);
		listBoisson.add(b2);
		
		Mockito.when(boissonsRepository.findBoisson("Water")).thenReturn(listBoisson);
	}

	@Test
	public void SearchSimpleController_thenResponseIsCorrect() throws Exception {
		mockMvc.perform(get("/recherche/rapide")
				.param("boisson", "Water"))
				.andExpect(status().isOk())
				.andExpect(view().name("boisson/see-boissons"));
	}

	@Test
	public void SearchAdvancedControllerOrderBest_thenResponseIsCorrect() throws Exception {
		//Test order best 
		mockMvc.perform(get("/recherche/avancee")
				.param("boisson", "Water")
				.param("categoriesId", "-1"))
				.andExpect(status().isOk()).andExpect(view().name("boisson/see-boissons"));
	}
	
	@Test
	public void SearchAdvancedControllerOrderNoCategorie_thenResponseIsCorrect() throws Exception {
		//Test order best 
		mockMvc.perform(get("/recherche/avancee")
				.param("boisson", "Water")
				.param("categoriesId", "-1", "1"))
				.andExpect(status().isOk()).andExpect(view().name("boisson/see-boissons"));
	}

	@Test
	public void SearchAdvancedControllerOrderAsc_thenResponseIsCorrect() throws Exception {
		//Test order best 
		mockMvc.perform(get("/recherche/avancee")
				.param("boisson", "Water")
				.param("order", "1")
				.param("categoriesId", "1"))
				.andExpect(status().isOk()).andExpect(view().name("boisson/see-boissons"));
	}
	
	@Test
	public void SearchAdvancedControllerOrderDsc_thenResponseIsCorrect() throws Exception {
		//Test order best 
		mockMvc.perform(get("/recherche/avancee")
				.param("order", "2"))
				.andExpect(status().isOk()).andExpect(view().name("boisson/see-boissons"));
	}

	@Test
	public void SearchAdvancedControllerOrderInvalid_thenResponseIsCorrect() throws Exception {
		//Test order best 
		mockMvc.perform(get("/recherche/avancee")
				.param("boisson", "")
				.param("order", "sdf"))
				.andExpect(status().isOk()).andExpect(view().name("boisson/see-boissons"));
	}
}
