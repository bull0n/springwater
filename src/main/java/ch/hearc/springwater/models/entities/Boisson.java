package ch.hearc.springwater.models.entities;

import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.springframework.web.multipart.MultipartFile;

import ch.hearc.springwater.security.Utilisateur;

@Entity
@Table(name = "boisson")
public class Boisson {
	@NotNull
	@NotEmpty
	private String nom;
	@Column(length = 1024)
	private String description;
	
	@Transient
	private MultipartFile file;
	private String fileURL;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "boisson_categorie", joinColumns = @JoinColumn(name = "boisson_id"), inverseJoinColumns = @JoinColumn(name = "categorie_id"))
	private Set<Categorie> categories;

	@Formula("(SELECT COALESCE(SUM(v.score),0) FROM vote v WHERE v.boisson_id = id)")
	private int score;

	@ManyToOne
	private Utilisateur owner;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public int getScore() {
		return score;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public Set<Categorie> getCategories() {
		return categories;
	}
	
	public Utilisateur getOwner() {
		return owner;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	
	public void setOwner(Utilisateur owner)
	{
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Boisson [nom=");
		builder.append(nom);
		builder.append(", description=");
		builder.append(description);
		builder.append(", file=");
		builder.append(file);
		builder.append(", fileURL=");
		builder.append(fileURL);
		builder.append(", categories=");
		builder.append(categories);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}
