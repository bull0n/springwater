package ch.hearc.springwater.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import ch.hearc.springwater.models.entities.Boisson;

public interface BoissonsRepository extends PagingAndSortingRepository<Boisson, Long> {
	
    @Query(value = "SELECT SUM(v.score) from Vote v WHERE boisson_id == ?1", nativeQuery = true)
    public int getBoissonScore(long id);
}
