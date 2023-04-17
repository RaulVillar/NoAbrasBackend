package Api.NoAbras.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name= "data")
public class CModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;
    @Column (name = "name", nullable = false)
    private String name;
    @Column (name = "theme", nullable = false)
    private String theme;
    @Column (name = "description", nullable = false)
    private String description;
    @Column (name = "location", nullable = false)
    private String location;
    @Column (columnDefinition = "LONGTEXT")
    private String urlImg;

}


