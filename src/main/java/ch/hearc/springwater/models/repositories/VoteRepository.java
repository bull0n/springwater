package ch.hearc.springwater.models.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.springwater.models.entities.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
