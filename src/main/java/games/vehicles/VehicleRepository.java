package games.vehicles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle,Integer> {
    @Override
    List<Vehicle> findAll();
}
