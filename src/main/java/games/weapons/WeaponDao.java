package games.weapons;

import games.heroes.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeaponDao {
    @Autowired
    public WeaponRepository repository;

    public void createWeapon(Weapon weapon) {
        repository.save(weapon);
    }

    public List<Weapon> getWeapons() {
        return repository.findAll();
    }

    public void updateWeapon(Weapon weapon) {
        repository.save(weapon);
    }

    public void deleteWeapon(Weapon weapon){
        repository.delete(weapon);
    }
}
