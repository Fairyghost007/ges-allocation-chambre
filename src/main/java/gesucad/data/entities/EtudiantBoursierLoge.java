package gesucad.data.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import gesucad.data.enums.TypeBourse;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EtudiantBoursierLoge extends EtudiantBoursier {
    private Chambre chambre;

    public EtudiantBoursierLoge(String numero, String nom, String prenom, String email,String telephone, Date dateNaissance, TypeBourse typeBourse, Chambre chambre) {
        super(numero, nom, prenom, email, telephone, dateNaissance, typeBourse);
        this.chambre = chambre;
    }
}
