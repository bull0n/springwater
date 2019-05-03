package ch.hearc.springwater.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.repositories.BoissonsRepository;
import ch.hearc.springwater.models.repositories.CategoriesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoissonControllerTest
{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	BoissonsRepository boissonsRepository;

	@MockBean
	CategoriesRepository categoriesRepository;

	@Before
	public void setUp()
	{
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

		Page<Boisson> page = Page.empty();
		Mockito.when(boissonsRepository.findAll(any(Pageable.class))).thenReturn(page);
		Mockito.when(boissonsRepository.findById(any())).thenReturn(Optional.of(b1));
	}

	@Test
	@WithMockUser(username = "admin", roles = { "USER" })
	public void whenBoissonController_thenResponseIsCorrect() throws Exception
	{
		mockMvc.perform(get("/boisson/")).andExpect(status().isOk()).andExpect(view().name("boisson/see-boissons"));
	}

	@Test
	@WithMockUser(username = "admin", roles = { "USER" })
	public void whenBoissonControllerPageable_thenResponseIsCorrect() throws Exception
	{
		mockMvc.perform(get("/boisson/page/{pageNum}", "1")).andExpect(status().isOk())
				.andExpect(view().name("boisson/see-boissons"));
	}

	@Test
	public void whenBoissonControllerDetails_thenResponseIsCorrect() throws Exception
	{
		mockMvc.perform(get("/boisson/{id}", "1")).andExpect(status().isOk())
				.andExpect(view().name("boisson/see-detail"));
	}

	@Test
	@WithMockUser(username = "admin", roles = { "USER" })
	public void whenBoissonControllerAdd_thenResponseIsCorrect() throws Exception
	{
		String nom = "B1";
		String description = "description";
		Boisson b = new Boisson();

		Path path = Paths.get("uploads/test/test.jpg").toAbsolutePath().normalize();
		String pathStr = path.toString();

		MockMultipartFile file = new MockMultipartFile("file", pathStr, "image/jpg", new FileInputStream(pathStr));

		b.setFile(file);
		b.setNom(nom);
		b.setDescription(description);

		mockMvc.perform(
				multipart("/boisson/save").file(file).param("nom", nom).param("description", description).with(csrf()))
				.andExpect(status().is3xxRedirection());
		Mockito.when(boissonsRepository.save(Mockito.any(Boisson.class))).thenReturn(b);
	}
}
