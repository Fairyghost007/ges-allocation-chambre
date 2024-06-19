package gesucad.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    private String numero;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Date dateNaissance;
}

