package gesucad.data.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import gesucad.data.enums.TypeBourse;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EtudiantBoursier extends Etudiant {
    private TypeBourse typeBourse;

    public EtudiantBoursier(String numero, String nom, String prenom, String email, String telephone, Date dateNaissance, TypeBourse typeBourse) {
        super(numero, nom, prenom, email, telephone, dateNaissance);
        this.typeBourse = typeBourse;
    }
}

