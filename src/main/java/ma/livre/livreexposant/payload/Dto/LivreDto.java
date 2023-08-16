package ma.livre.livreexposant.payload.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LivreDto {
    private int livreId;
    private String titre;
    private String auteur;
    private String editeur;
    private String dateEdition;
    private double prix;
    private String isbn;
    private ExposantDto exposant;
    private int exposantId;
}

