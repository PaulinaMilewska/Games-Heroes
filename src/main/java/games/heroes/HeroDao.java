package games.heroes;

import games.heroes.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroDao {

    @Autowired
    public HeroRepository repository;

    public void createHero(Hero hero) {
        repository.save(hero);
    }

    public List<Hero> getHeroes() {
        return repository.findAll();
    }

    public void updateHero(Hero hero) {
        repository.save(hero);
    }

    public void deleteHero(Hero hero){
        repository.delete(hero);
    }

}
