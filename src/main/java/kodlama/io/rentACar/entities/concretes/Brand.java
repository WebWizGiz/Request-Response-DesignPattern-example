package kodlama.io.rentACar.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "brands")
@Data // getter + setter
@AllArgsConstructor // with parameters
@NoArgsConstructor // without parameters
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "brand")
    private List<Model> models;



// Brand -->id,name,quantity
// GetAllBrandsResponse -->id,name

    //mapping

}


