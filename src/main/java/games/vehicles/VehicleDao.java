package games.vehicles;

import games.heroes.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleDao {

@Autowired
public VehicleRepository repository;

    public void createVehicle(Vehicle vehicle) {
        repository.save(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return repository.findAll();
    }

    public void updateVehicle(Vehicle vehicle) {
        repository.save(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle){
        repository.delete(vehicle);
    }

}
