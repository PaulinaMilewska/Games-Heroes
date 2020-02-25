package games.weapons;

import games.vehicles.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponRepository extends CrudRepository<Weapon,Integer> {
    @Override
    List<Weapon> findAll();
}
