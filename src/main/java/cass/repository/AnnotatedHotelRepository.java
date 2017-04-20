package cass.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cass.domain.Hotel;

public interface AnnotatedHotelRepository extends CrudRepository<Hotel, UUID> {
	@Query("select * from hotels where state =?0 ALLOW FILTERING")
	List<Hotel> findByState(String state);
}
