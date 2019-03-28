package ch.hearc.springwater.security;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	//@Column(unique = true)
	private String nom;

	@ManyToMany(mappedBy = "roles")
	private Set<Utilisateur> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Utilisateur> getUsers() {
		return users;
	}

	public void setUsers(Set<Utilisateur> users) {
		this.users = users;
	}
}
