package ch.hearc.springwater.controllers;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.hearc.springwater.config.SecurityConfig;
import ch.hearc.springwater.exceptions.MotDePasseConfirmationException;
import ch.hearc.springwater.security.Role;
import ch.hearc.springwater.security.RoleRepository;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.security.UtilisateurRepository;

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
	
	private static final String SIGNUP = "security/signup";
	private static final String MDP_PAS_IDENTIQUE = "Le mot de passe ne correspond pas";
	
	@GetMapping(value = "/signup")
	public String getCategories(Map<String, Object> model)
	{
		this.putNewUser(model);

		return SIGNUP;
	}

	@PostMapping(value = "/register")
	public String save(@ModelAttribute @Valid Utilisateur user, BindingResult bindingResult,
			Map<String, Object> model)
	{
		if(bindingResult.hasErrors())
		{
			return SIGNUP;
		}
		
		try
		{
			user = this.validateMdp(user);
		}
		catch (MotDePasseConfirmationException e)
		{
			model.put("erreurs", e.getMessage());
			this.putNewUser(model);
			return SIGNUP;
		}

		Role roleUser = roleRepo.findByNom("ROLE_USER");

		Set<Role> rolesUser = new HashSet<>();
		rolesUser.add(roleUser);
		user.setRoles(rolesUser);
		
		userRepo.save(user);

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				user.getNomUtilisateur(), user.getMotDePasse());
		try
		{
			securityConfig.authenticationManagerBean().authenticate(authToken);
		}
		catch (Exception e)
		{
			return SIGNUP;
		}

		if(authToken.isAuthenticated())
		{
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}

		return "redirect:/boisson/";
	}

	private Utilisateur validateMdp(Utilisateur user) throws MotDePasseConfirmationException
	{
		if(!user.getMotDePasse().equals(user.getMotDePasseConfirmation()))
		{
			throw new MotDePasseConfirmationException(MDP_PAS_IDENTIQUE);
		}

		user.setNomUtilisateur(user.getNomUtilisateur());
		user.setMotDePasse(bCryptPasswordEncoder.encode(user.getMotDePasse()));

		return user;
	}

	private void putNewUser(Map<String, Object> model)
	{
		model.put("utilisateur", new Utilisateur());
	}
}
