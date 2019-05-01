package ch.hearc.springwater.controllers;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.entities.Vote;
import ch.hearc.springwater.models.repositories.BoissonsRepository;
import ch.hearc.springwater.models.repositories.CategoriesRepository;
import ch.hearc.springwater.models.repositories.VoteRepository;
import ch.hearc.springwater.security.Role;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.security.UtilisateurRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FavoriteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	BoissonsRepository boissonsRepository;

	@MockBean
	UtilisateurRepository utilisateursRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final String USERNAME = "User";
	private static final String PASSWORD = "Password";
	private static final String ROLE = "ROLE_USER";
	
	private static final Long ID_BOISSON = 1L;
	private static final Long ID_USER = 1L;
	private static final String CONTENT_TYPE = "application/json";
	
	@Before
	public void setUp() {
		Boisson boisson1 = new Boisson();
		boisson1.setId(ID_BOISSON);
		boisson1.setNom("Water");
		boisson1.setDescription("Best drink ever");
		boisson1.setCategories(new HashSet<>());
		Mockito.when(boissonsRepository.findById(boisson1.getId())).thenReturn(Optional.of(boisson1));
		
		Boisson boisson2 = new Boisson();
		boisson2.setId(ID_BOISSON);
		boisson2.setNom("Beer");
		boisson2.setDescription("Best drink ever");
		boisson2.setCategories(new HashSet<>());
		Mockito.when(boissonsRepository.findById(boisson2.getId())).thenReturn(Optional.of(boisson2));
		
		Set<Boisson> boissonsFavorite = new HashSet<>();
		boissonsFavorite.add(boisson1);
		
		Utilisateur user1 = new Utilisateur();
		user1.setId(ID_USER);
		user1.setNomUtilisateur(USERNAME);
		user1.setMotDePasse(bCryptPasswordEncoder.encode(PASSWORD));
		user1.setBoissonsFavorite(boissonsFavorite);
		Mockito.when(utilisateursRepository.findByNomUtilisateur(user1.getNomUtilisateur())).thenReturn(user1);
		
		Role roleUser = new Role();
		roleUser.setNom(ROLE);
		
		Set<Role> roles = new HashSet<Role>();
		user1.setRoles(roles);
	}
	
	@Test
	public void WhenAddFavorite_thenResponseIsCorrect() throws Exception {
		mockMvc.perform(post("/favorite/add/{id}", "2").with(user(USERNAME).roles("USER")).with(csrf()).contentType(CONTENT_TYPE)).andExpect(status().isOk());
	}
	
	@Test
	public void WhenRemoveFavorite_thenResponseIsCorrect() throws Exception {
		mockMvc.perform(post("/favorite/remove/{id}", "1").with(user(USERNAME).roles("USER")).with(csrf()).contentType(CONTENT_TYPE)).andExpect(status().isOk());
	}
}
