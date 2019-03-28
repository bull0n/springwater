package ch.hearc.springwater.models.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import ch.hearc.springwater.models.repositories.CategoriesRepository;
import ch.hearc.springwater.models.repositories.VoteRepository;

@Entity
@Table(name="boisson")
public class Boisson
{
	private String nom;

	@Autowired
	public VoteRepository voteRepository;
	
	@Column(length=1024)
	private String description;
	private String linkImage;
	
	@ManyToMany
	@JoinColumn
	private List<Categorie> categories = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Vote> votes = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public int getScore() {
		return voteRepository.getBoissonScore(this.id);
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getDescription()
	{
		return description;
	}
	
	public List<Categorie> getCategories()
	{
		return categories;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getLinkImage()
	{
		return linkImage;
	}

	public void setLinkImage(String linkImage)
	{
		this.linkImage = linkImage;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setCategories(List<Categorie> categories)
	{
		this.categories = categories;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Boisson [nom=");
		builder.append(nom);
		builder.append(", description=");
		builder.append(description);
		builder.append(", linkImage=");
		builder.append(linkImage);
		builder.append(", id=");
		builder.append(id);
		builder.append(", categories=");
		builder.append(categories);
		builder.append("]");
		return builder.toString();
	}
}
