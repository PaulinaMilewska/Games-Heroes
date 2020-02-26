package games.weapons;

import games.heroes.Hero;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "weapons")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "names")
    @NonNull
    @Getter
    @Setter
    private String name;
    @Column(name = "knockbacks")
    @NonNull
    @Getter
    @Setter
    private double knockback; //max 20 (odrzut)
    @Column(name = "usetimes")
    @NonNull
    @Getter
    @Setter
    private int useTime; //regeneration time w "ticks"
    @Column(name = "values")
    @NonNull
    @Getter
    @Setter
    private int value; //in silver coins

    @JoinTable(name = "weapon_hero",
            joinColumns = {@JoinColumn(name = "weaponId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "heroId", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "hero_id", nullable = false, referencedColumnName = "id")
    @ToString.Exclude
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    public Set<Hero> heroSet;

    public Set<Hero> getHeroSet() {
        if (heroSet == null) {
            heroSet = new HashSet<>();
        }
        return heroSet;
    }

    public Weapon() {
    }

    public Weapon( Set<Hero> heroSet, String name, double knockback, int useTime, int value) {
        this.heroSet = heroSet;
        this.name = name;
        this.knockback = knockback;
        this.useTime = useTime;
        this.value = value;
    }

    public Weapon( Integer id, String name, double knockback, int useTime, int value) {
        this.id = id;
        this.name = name;
        this.knockback = knockback;
        this.useTime = useTime;
        this.value = value;
    }

}
