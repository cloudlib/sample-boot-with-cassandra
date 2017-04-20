package cass.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import cass.domain.Hotel;

@Repository
public class CassandraHotelRepository implements HotelRepository {
    
    private final CassandraOperations cassandraTemplate;
    
    public CassandraHotelRepository(CassandraOperations cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }
    
    @Override
    public Hotel save(Hotel hotel) {
        return this.cassandraTemplate.insert(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return this.cassandraTemplate.update(hotel);
    }

    @Override
    public Hotel findOne(UUID hotelId) {
        return this.cassandraTemplate.selectOneById(Hotel.class, hotelId);
    }

    @Override
    public void delete(UUID hotelId) {
        this.cassandraTemplate.deleteById(Hotel.class, hotelId);
    }

    @Override
    public List<Hotel> findByState(String state) {
        Select select = QueryBuilder.select().from("hotels_by_state");
        select.where(QueryBuilder.eq("state", state));
        System.out.println("select: "+select);
        return this.cassandraTemplate.select(select, Hotel.class);
    }
}
