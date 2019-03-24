package ch.hearc.springwater.controllers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.hearc.springwater.config.SecurityConfig;
import ch.hearc.springwater.security.Role;
import ch.hearc.springwater.security.RoleRepository;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.security.UtilisateurDto;
import ch.hearc.springwater.security.UtilisateurRepository;
import ch.hearc.springwater.security.exceptions.MotDePasseConfirmationException;
import ch.hearc.springwater.security.exceptions.MotDePasseException;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
	@Autowired
	UtilisateurRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	SecurityConfig securityConfig;

	@GetMapping(value = "/signup")
	public String getCategories(Map<String, Object> model)
	{
		this.putNewUser(model);

		return "security/signup";
	}

	@PostMapping(value = "/register")
	public String save(UtilisateurDto userDto, Map<String, Object> model)
	{
		Utilisateur user;

		try
		{
			user = validateUser(userDto);
		}
		catch (MotDePasseConfirmationException | MotDePasseException e)
		{
			model.put("erreurs", e.getMessage());
			this.putNewUser(model);
			return "security/signup";
		}

		userRepo.save(user);
		Role roleUser = roleRepo.findByNom("ROLE_USER");

		Set<Role> rolesUser = new HashSet<>();
		rolesUser.add(roleUser);
		user.setRoles(rolesUser);

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getNomUtilisateur(),
				userDto.getMotDePasse());
		try
		{
			securityConfig.authenticationManagerBean().authenticate(authToken);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if(authToken.isAuthenticated())
		{
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}

		return "redirect:/boisson/";
	}

	private Utilisateur validateUser(UtilisateurDto userDto) throws MotDePasseConfirmationException, MotDePasseException
	{
		Utilisateur user = new Utilisateur();

		if(!userDto.getMotDePasse().equals(userDto.getMotDePasseConfirmation()))
		{
			throw new MotDePasseConfirmationException(MDP_PAS_IDENTIQUE);
		}

		if(userDto.getMotDePasse().length() < 8)
		{
			throw new MotDePasseException(MDP_TROP_COURT);
		}

		user.setNomUtilisateur(userDto.getNomUtilisateur());
		user.setMotDePasse(bCryptPasswordEncoder.encode(userDto.getMotDePasse()));

		return user;
	}

	private void putNewUser(Map<String, Object> model)
	{
		model.put("utilisateur", new UtilisateurDto());
	}

	private final String MDP_TROP_COURT = "Le mot de passe est trop court";
	private final String MDP_PAS_IDENTIQUE = "Le mot de passe ne correspond pas";
}
