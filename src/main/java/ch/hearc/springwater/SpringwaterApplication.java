package ch.hearc.springwater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.hearc.springwater.models.repositories.CategoriesRepository;

@SpringBootApplication
public class SpringwaterApplication {

	@Autowired
	public CategoriesRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringwaterApplication.class, args);
	}
}
