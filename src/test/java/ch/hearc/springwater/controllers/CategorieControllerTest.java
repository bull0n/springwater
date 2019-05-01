package ch.hearc.springwater.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.repositories.CategoriesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategorieControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CategoriesRepository categoriesRepository;

	// @Mock
	private List<Categorie> listCategorie = new ArrayList<>();

	@Before
	public void setUp()
	{
		//List<Categorie> listCategorie = new ArrayList<>();
		Categorie c1 = new Categorie();
		c1.setId(1L);
		c1.setNom("Cat 1");
		Categorie c2 = new Categorie();
		c2.setId(1L);
		c2.setNom("Cat 2");

		listCategorie.add(c1);
		listCategorie.add(c2);

		Mockito.when(categoriesRepository.findAll()).thenReturn(listCategorie);
		Mockito.when(categoriesRepository.findById(1L)).thenReturn(Optional.of(c1));
		//Mockito.doNothing().when(categoriesRepository).deleteById(Mockito.anyLong()).then(id -> listCategorie.remove(c2));
	}

	@Test
	public void whenCategorieController_thenResponseIsCorrect() throws Exception
	{
		mockMvc.perform(get("/categorie/")).andExpect(status().isOk())
				.andExpect(view().name("categories/see-categories"));
	}

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void whenBoissonControllerAdd_thenResponseIsCorrect() throws Exception
	{
		mockMvc.perform(get("/categorie/add")).andExpect(status().isOk())
				.andExpect(view().name("categories/categorie-add"));
	}

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void whenBoissonControllerEdit_thenResponseIsCorrect() throws Exception
	{
		mockMvc.perform(get("/categorie/edit/1")).andExpect(status().isOk())
				.andExpect(view().name("categories/categorie-edit"));
	}

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void whenBoissonControllerSave_thenResponseIsCorrect() throws Exception
	{
		String nom = "C3";
		Categorie c = new Categorie();
		c.setNom(nom);
		Mockito.when(categoriesRepository.save(Mockito.any(Categorie.class))).thenReturn(c);
		mockMvc.perform(post("/categorie/save").param("nom", nom).with(csrf())).andExpect(status().is3xxRedirection());
	}
	
	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	public void whenBoissonControllerUpdate_thenResponseIsCorrect() throws Exception
	{
		String nom = "C4";
		Categorie c = new Categorie();
		c.setNom(nom);
		Mockito.when(categoriesRepository.save(Mockito.any(Categorie.class))).thenReturn(c);
		mockMvc.perform(post("/categorie/save").param("nom", nom).with(csrf())).andExpect(status().is3xxRedirection());
	}
}
