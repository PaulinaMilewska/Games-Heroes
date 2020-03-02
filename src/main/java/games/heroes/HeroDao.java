package games.heroes;

import games.heroes.Hero;
import games.weapons.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class HeroDao {

    @Autowired
    public HeroRepository repository;

    @Autowired
    private EntityManager entityManager;

    public void createHero(Hero hero) {
        repository.save(hero);
    }

    public List<Hero> getHeroes() {
        return repository.findAll();
    }

    public void updateHero(Hero hero) {
        repository.save(hero);
    }

    @Transactional
    public void deleteHero(Hero hero){

        hero = entityManager.find(Hero.class, hero.getId());
        for (Weapon weapon : hero.getWeaponSet()) {
            if (weapon.getHeroSet().size() == 1) {
                entityManager.remove(weapon);
            } else {
                weapon.getHeroSet().remove(hero);
            }
        }

        repository.delete(hero);
    }

}
