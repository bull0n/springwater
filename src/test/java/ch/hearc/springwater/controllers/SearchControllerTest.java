package ch.hearc.springwater.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
		Boisson b = new Boisson();
		b.setId(1L);
		b.setNom("Water");
		b.setDescription("Best drink ever");
		b.setCategories(new HashSet<>());

		List<Boisson> listBoisson = new ArrayList<>();
		listBoisson.add(b);

		Mockito.when(boissonsRepository.findBoisson("Water")).thenReturn(listBoisson);

		Categorie c = new Categorie();
		c.setId(1L);
		c.setNom("Cat1");

		List<Categorie> listCategorie = new ArrayList<>();
		listCategorie.add(c);

		Mockito.when(categoriesRepository.findAll()).thenReturn(listCategorie);
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
	public void SearchAdvancedControllerOrderAsc_thenResponseIsCorrect() throws Exception {
		//Test order best 
		mockMvc.perform(get("/recherche/avancee")
				.param("boisson", "Water")
				.param("order", "1"))
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
				.param("order", "sdf"))
				.andExpect(status().isOk()).andExpect(view().name("boisson/see-boissons"));
	}
}
