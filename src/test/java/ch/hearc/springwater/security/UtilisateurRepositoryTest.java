package ch.hearc.springwater.security;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UtilisateurRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UtilisateurRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private final String NOM = "Jean-Test";
	private final String MDP = "password";
	private final String ROLE = "ROLE_USER";
	
	@Test
	public void givenUser_whenPersistUser_thenUserIsPersisted() {
		Role roleUser = new Role();
		roleUser.setNom(ROLE);
		entityManager.persist(roleUser);

		Utilisateur user = new Utilisateur();
		user.setNomUtilisateur(NOM);
		user.setMotDePasse(bCryptPasswordEncoder.encode(MDP));
		user.setVotes(new ArrayList<>());
		entityManager.persist(user);

		Utilisateur userSearched = userRepo.findByNomUtilisateur(NOM);

		assertTrue(userSearched != null);
	}

	@Test
	public void givenUser_whenPersistUser_thenUserHasTheRightName() {
		Role roleUser = new Role();
		roleUser.setNom(ROLE);
		entityManager.persist(roleUser);

		Utilisateur user = new Utilisateur();
		user.setNomUtilisateur(NOM);
		user.setMotDePasse(bCryptPasswordEncoder.encode(MDP));
		user.setVotes(new ArrayList<>());
		entityManager.persist(user);

		Utilisateur userSearched = userRepo.findByNomUtilisateur(NOM);
		String name = userSearched.getNomUtilisateur();

		assertTrue(name.equals(NOM));
	}

	@Test
	public void givenUser_whenPersistUser_thenUserHasTheRightPassword() {
		Role roleUser = new Role();
		roleUser.setNom(ROLE);
		entityManager.persist(roleUser);

		Utilisateur user = new Utilisateur();
		user.setNomUtilisateur(NOM);
		user.setMotDePasse(bCryptPasswordEncoder.encode(MDP));
		user.setVotes(new ArrayList<>());
		entityManager.persist(user);

		Utilisateur userSearched = userRepo.findByNomUtilisateur(NOM);
		String password = userSearched.getMotDePasse();

		assertTrue(bCryptPasswordEncoder.matches(MDP, password));
	}

	@Test
	public void givenUser_whenPersistUser_thenUserHasTheRightRole() {
		Role roleUser = new Role();
		roleUser.setNom(ROLE);
		entityManager.persist(roleUser);
		Set<Role> rolesUser = new HashSet<Role>();
		rolesUser.add(roleUser);

		Utilisateur user = new Utilisateur();
		user.setNomUtilisateur(NOM);
		user.setMotDePasse(bCryptPasswordEncoder.encode(MDP));
		user.setVotes(new ArrayList<>());
		user.setRoles(rolesUser);
		entityManager.persist(user);

		Utilisateur userSearched = userRepo.findByNomUtilisateur(NOM);

		for (Role role : userSearched.getRoles()) {
			assertTrue(role.getNom().equals(ROLE));
		}
	}

	@Test
	public void givenUser_whenPersistUser_thenUserHasTheRightVotes() {
		// Todo
	}
}
