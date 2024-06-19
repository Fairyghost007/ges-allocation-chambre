package gesucad.data.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EtudiantNonBoursier extends Etudiant {
    private String adresse;

    public EtudiantNonBoursier(String numero, String nom, String prenom, String email,String telephone, Date dateNaissance, String adresse) {
        super(numero, nom, prenom, email, telephone, dateNaissance);
        this.adresse = adresse;
    }
}

