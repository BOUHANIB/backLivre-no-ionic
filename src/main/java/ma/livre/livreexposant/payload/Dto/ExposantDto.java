package ma.livre.livreexposant.payload.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ExposantDto {
    private int exposantId;
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
}
