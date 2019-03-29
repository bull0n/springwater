package ch.hearc.springwater;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.hearc.springwater.models.repositories.CategoriesRepository;
import ch.hearc.springwater.security.Role;
import ch.hearc.springwater.security.RoleRepository;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.security.UtilisateurRepository;

@SpringBootApplication
public class SpringwaterApplication {

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UtilisateurRepository userRepo;

	@Autowired
	public CategoriesRepository repository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringwaterApplication.class, args);
	}

	@PostConstruct
	public void initData() {
		Role roleAdmin = new Role();
		roleAdmin.setNom("ROLE_ADMIN");
		roleRepo.save(roleAdmin);

		Role roleUser = new Role();
		roleUser.setNom("ROLE_USER");
		roleRepo.save(roleUser);

		Utilisateur admin = new Utilisateur();
		admin.setNomUtilisateur("admin");
		admin.setMotDePasse(bCryptPasswordEncoder.encode("password"));
		admin.setVotes(new ArrayList<>());
		
		Set<Role> roles = new HashSet<>();
		roles.add(roleAdmin);
		admin.setRoles(roles);

		userRepo.save(admin);

		Utilisateur user = new Utilisateur();
		user.setNomUtilisateur("user");
		user.setMotDePasse(bCryptPasswordEncoder.encode("password"));
		user.setVotes(new ArrayList<>());
		
		Set<Role> rolesUser = new HashSet<>();
		rolesUser.add(roleUser);
		user.setRoles(rolesUser);

		userRepo.save(user);
	}
}
