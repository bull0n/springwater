package ch.hearc.springwater.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="personne")
public class Boisson
{
	private String nom;
	private String description;
	private String linkImage;
	
	@ManyToMany
	private List<Categorie> categories = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
		builder.append("]");
		return builder.toString();
	}
}
