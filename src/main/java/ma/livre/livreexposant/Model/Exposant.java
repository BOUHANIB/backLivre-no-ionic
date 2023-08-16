package ma.livre.livreexposant.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exposant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exposantId;
    private String nom;
    private String pays;
    private String mail;
    private String password;
    private String telephone;
    private String siteWeb;
    private String adresse;
    private String responsableSalle;
    private String responsable;
    private String specialite;
    private String localisation;

    @OneToMany(mappedBy = "exposant" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Set<Livre> livre;

}
