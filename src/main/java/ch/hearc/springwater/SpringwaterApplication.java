package ch.hearc.springwater;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.hearc.springwater.models.entities.Categorie;
import ch.hearc.springwater.models.repositories.CategoriesRepository;

@SpringBootApplication
public class SpringwaterApplication {
	
	@Autowired
	public CategoriesRepository repository;
	
	@PostConstruct
	public void init()
	{
		Categorie c = new Categorie();
		c.setNom("Wine");
		repository.save(c);
		
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringwaterApplication.class, args);
	}

}
