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
public class VoteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	BoissonsRepository boissonsRepository;

	@MockBean
	UtilisateurRepository utilisateursRepository;
	
	@MockBean
	VoteRepository votesRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final String USERNAME = "User";
	private static final String PASSWORD = "Password";
	private static final String ROLE = "ROLE_USER";
	
	@Before
	public void setUp() {
		Boisson boisson1 = new Boisson();
		boisson1.setId(1L);
		boisson1.setNom("Water");
		boisson1.setDescription("Best drink ever");
		boisson1.setCategories(new HashSet<>());
		Mockito.when(boissonsRepository.findById(boisson1.getId())).thenReturn(Optional.of(boisson1));
		
		Utilisateur user1 = new Utilisateur();
		user1.setId(1L);
		user1.setNomUtilisateur(USERNAME);
		user1.setMotDePasse(bCryptPasswordEncoder.encode(PASSWORD));
		Mockito.when(utilisateursRepository.findByNomUtilisateur(user1.getNomUtilisateur())).thenReturn(user1);
		
		Vote vote = new Vote();
		vote.setId(1L);
		vote.setUser(user1);
		vote.setBoisson(boisson1);
		vote.setScore(0);
		
		Role roleUser = new Role();
		roleUser.setNom(ROLE);
		
		List<Vote> votes = new ArrayList<Vote>();
		Set<Role> roles = new HashSet<Role>();
		user1.setVotes(votes);
		user1.setRoles(roles);
		
		Mockito.when(votesRepository.getScore(boisson1.getId())).thenReturn(vote.getScore());
	}
	
	@Test
	public void WhenUpVote_thenResponseIsCorrect() throws Exception {
		mockMvc.perform(post("/vote/downvote/{id}", "1").with(user(USERNAME).roles("USER")).with(csrf()).contentType("application/json")).andExpect(status().isOk());
	}
	
	@Test
	public void WhenDownVote_thenResponseIsCorrect() throws Exception {
		mockMvc.perform(post("/vote/downvote/{id}", "1").with(user(USERNAME).roles("USER")).with(csrf()).contentType("application/json")).andExpect(status().isOk());
	}
}
