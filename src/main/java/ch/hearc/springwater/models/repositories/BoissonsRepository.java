package ch.hearc.springwater.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import ch.hearc.springwater.models.entities.Boisson;

public interface BoissonsRepository extends PagingAndSortingRepository<Boisson, Long> {
	 
	@Query(value = "(select * FROM Boisson b WHERE b.nom LIKE %:searched% ORDER BY b.nom )"
			+ "UNION DISTINCT"
			+ "(select * FROM Boisson b WHERE b.description LIKE %:searched% ORDER BY b.nom )"
			, nativeQuery = true)
	List<Boisson> findBoissonSimple(@Param("searched")String searched);
}
