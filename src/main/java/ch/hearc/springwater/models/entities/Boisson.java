package ch.hearc.springwater.models.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

	@ManyToMany
	@JoinTable(name = "boisson_categorie", joinColumns = @JoinColumn(name = "boisson_id"), inverseJoinColumns = @JoinColumn(name = "categorie_id"))
	Set<Categorie> categories;
	
	@ManyToMany
	@JoinTable(name = "user_favorite_boisson", joinColumns = @JoinColumn(name = "boisson_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<Utilisateur> userFavoriteBoisson;

	@OneToMany(mappedBy = "boisson", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vote> votes = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public boolean isFavorite(Utilisateur user)
	{
		return this.userFavoriteBoisson.contains(user);
	}

	public int getScore() {
		int score = votes.parallelStream().mapToInt(v -> v.getScore()).reduce(0, Integer::sum);
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

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFile()
	{
		return file;
	}

	public void setFile(MultipartFile file)
	{
		this.file = file;
	}

	public String getFileURL()
	{
		return fileURL;
	}

	public void setFileURL(String fileURL)
	{
		this.fileURL = fileURL;
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
	public String toString()
	{
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
		builder.append(", votes=");
		builder.append(votes);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}	
}
