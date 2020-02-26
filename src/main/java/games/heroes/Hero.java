package games.heroes;

import games.vehicles.Vehicle;
import games.weapons.Weapon;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "heroes")
@Data
//@RequiredArgsConstructor
@AllArgsConstructor
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(name = "ID")
    @Getter
    @Setter
    private Integer id;
    @Column(name = "names")
    @NonNull
    @Getter
    @Setter
    private String name;
    @Column(name = "forces")
    @NonNull
    @Getter
    @Setter
    private int force;
    @Column(name = "gems")
    @NonNull
    @Getter
    @Setter
    private String gem; //klejnot

    @OneToMany(mappedBy = "hero", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Vehicle> vehicles;

    @ManyToMany(mappedBy = "heroSet", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Weapon> weaponSet;

    public Hero() {
    }

    public Hero(Integer id, String name, int force, String gem) {
        this.id = id;
        this.name = name;
        this.force = force;
        this.gem = gem;
    }

    public Hero( String name, int force, String gem) {
        this.name = name;
        this.force = force;
        this.gem = gem;
    }

    public Set<Weapon> getWeaponSet() {
        if (weaponSet == null) {
            weaponSet = new HashSet<>();
        }
        return weaponSet;
    }
}
