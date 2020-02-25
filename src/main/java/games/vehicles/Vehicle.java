package games.vehicles;

import games.heroes.Hero;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "names")
    @NonNull
    @Getter
    @Setter
    private String name;
    @Column(name = "speeds")
    @NonNull
    @Getter
    @Setter
    private int speed;
    @Column(name = "values")
    @NonNull
    @Getter
    @Setter
    private int value;

    @ManyToOne
    @JoinColumn(name = "hero_id", nullable = false, referencedColumnName="id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    public Hero heroes;

    public Vehicle() {
    }
}
