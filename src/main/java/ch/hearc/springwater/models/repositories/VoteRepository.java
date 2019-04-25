package ch.hearc.springwater.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ch.hearc.springwater.models.entities.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

	@Query(value = "SELECT SUM(v.score) FROM Vote v WHERE v.boisson_id = :id", nativeQuery = true)
	public int getScore(@Param("id") Long boissonId);
}
