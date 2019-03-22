package ch.hearc.springwater.security;

import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
	Utilisateur findByNomUtilisateur(String nomUtilisateur);
}
