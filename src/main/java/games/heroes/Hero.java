package games.heroes;

import games.vehicles.Vehicle;
import games.weapons.Weapon;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "heroes")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @OneToMany(mappedBy = "heroes", cascade = CascadeType.REMOVE,  fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Vehicle> vehicles;

    @ManyToMany(mappedBy = "heroSet",  fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Weapon> weaponSetSet;

    public Hero() {
    }
}
