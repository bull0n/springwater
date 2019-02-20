package ch.hearc.springwater.models.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.springwater.models.entities.Boisson;
import ch.hearc.springwater.models.entities.Categorie;

public interface CategoriesRepository extends CrudRepository<Categorie, Long>
{

}
