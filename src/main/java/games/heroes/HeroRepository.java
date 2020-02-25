package games.heroes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero,Integer> {
//public interface HeroRepository extends CrudRepository<Hero,Integer> {
    @Override
    List<Hero> findAll();
}
