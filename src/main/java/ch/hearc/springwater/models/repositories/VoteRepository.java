package ch.hearc.springwater.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ch.hearc.springwater.models.entities.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {
	
    @Query(value = "SELECT SUM(v.score) from Vote v WHERE boisson_id == ?1", nativeQuery = true)
    int getBoissonScore(long id);
    
}
