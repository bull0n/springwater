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

@Entity
@Table(name = "boisson")
public class Boisson {
	private String nom;

	@Column(length = 1024)
	private String description;
	private String linkImage;

//	@ManyToMany
//	@JoinTable(
//	  name = "course_like", 
//	  joinColumns = @JoinColumn(name = "student_id"), 
//	  inverseJoinColumns = @JoinColumn(name = "course_id"))
//	Set<Course> likedCourses;
//	
	@ManyToMany
	@JoinTable(
	  name = "boisson_categorie", 
	  joinColumns = @JoinColumn(name = "boisson_id"), 
	  inverseJoinColumns = @JoinColumn(name = "categorie_id"))
	Set<Categorie> categories;
//	
//	@ManyToMany(mappedBy="boissons")
//	@JoinColumn
//	private List<Categorie> categories = new ArrayList<>();

	@OneToMany(mappedBy = "boisson", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vote> votes = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public int getScore() {
		System.out.println(votes.size());
		int score = votes.parallelStream().mapToInt(v -> v.getScore()).reduce(0, Integer::sum);
		System.out.println(this.id + " - " + score);
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

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
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
