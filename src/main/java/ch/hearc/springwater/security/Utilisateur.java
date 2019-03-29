package ch.hearc.springwater.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ch.hearc.springwater.models.entities.Vote;

@Entity
@Table(name = "user")
public class Utilisateur
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Column(unique = true, length = 50)
	private String nomUtilisateur;
	
	@NotNull
	@NotEmpty
	@Size(min = 8)
	private String motDePasse;
	@Transient
	private String motDePasseConfirmation;

	@ManyToMany
	private Set<Role> roles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Vote> votes = new HashSet<>();

	public Long getId()
	{
		return id;
	}

	public Set<Vote> getVotes()
	{
		return votes;
	}

	public void setVotes(Set<Vote> votes)
	{
		this.votes = votes;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNomUtilisateur()
	{
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur)
	{
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse()
	{
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse)
	{
		this.motDePasse = motDePasse;
	}

	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}

	public String getMotDePasseConfirmation()
	{
		return motDePasseConfirmation;
	}

	public void setMotDePasseConfirmation(String motDePasseConfirmation)
	{
		this.motDePasseConfirmation = motDePasseConfirmation;
	}
}
