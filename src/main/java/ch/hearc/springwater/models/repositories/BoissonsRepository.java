package ch.hearc.springwater.models.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.springwater.models.entities.Boisson;

public interface BoissonsRepository  extends CrudRepository<Boisson, Long>
{

}
