package ch.hearc.springwater.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import ch.hearc.springwater.models.entities.Boisson;

public interface BoissonsRepository  extends PagingAndSortingRepository<Boisson, Long>
{

}
