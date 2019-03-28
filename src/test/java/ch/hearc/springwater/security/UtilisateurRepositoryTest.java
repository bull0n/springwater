package ch.hearc.springwater.security;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UtilisateurRepositoryTest
{
	@Autowired
	private UtilisateurRepository userRepo;
	@Autowired 
	private RoleRepository roleRepo;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final String NOM = "Jean-Test";
	
	@Test
	public void givenUser_whenPersistUser_thenUserIsPersisted()
	{
		Role roleUser = new Role();
		roleUser.setNom("ROLE_USER");
		roleRepo.save(roleUser);

		Utilisateur user = new Utilisateur();
		user.setNomUtilisateur(NOM);
		user.setMotDePasse(bCryptPasswordEncoder.encode("password"));
		user.setVotes(new HashSet<>());
		
		Utilisateur userSearched = userRepo.findByNomUtilisateur(NOM);
		
		assertTrue(userSearched != null);
	}
}
