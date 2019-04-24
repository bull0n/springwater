package ch.hearc.springwater.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.hearc.springwater.security.Role;
import ch.hearc.springwater.security.Utilisateur;
import ch.hearc.springwater.security.UtilisateurRepository;

@Service
public class UtilisateurDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		Utilisateur utilisateur = utilisateurRepository.findByNomUtilisateur(username);
		if (utilisateur == null) {
			throw new UsernameNotFoundException(username);
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		for (Role role : utilisateur.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getNom()));
		}

		return new User(utilisateur.getNomUtilisateur(), utilisateur.getMotDePasse(), grantedAuthorities);
	}

	public Utilisateur loadCurrentUser() {
		Object userLoggedIn = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (userLoggedIn instanceof String) {
			return null;
		}

		String usernom = ((User) userLoggedIn).getUsername();
		return utilisateurRepository.findByNomUtilisateur(usernom);
	}
}
