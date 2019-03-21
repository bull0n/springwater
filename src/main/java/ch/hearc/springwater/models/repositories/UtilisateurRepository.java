package ch.hearc.springwater.models.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.springwater.models.entities.Utilisateur;

public interface UtilisateurRepository  extends CrudRepository<Utilisateur, Long>
{
	Utilisateur findByNomUtilisateur(String nomUtilisateur);
}
