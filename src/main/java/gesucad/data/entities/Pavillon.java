package gesucad.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Pavillon {
    private String numero;
    private int numeroEtage;
    private List<Chambre> chambres = new ArrayList<>();

    public Pavillon(String numero, int numeroEtage) {
        this.numero = numero;
        this.numeroEtage = numeroEtage;
    }

    public void ajouterChambre(Chambre chambre) {
        chambre.setPavillon(this); 
        chambres.add(chambre);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pavillon{");
        sb.append("numero='").append(numero).append('\'');
        sb.append(", numeroEtage=").append(numeroEtage);
        sb.append(", chambres=[");
        
        for (Chambre chambre : chambres) {
            sb.append("\n\t").append(chambre); 
        }

        sb.append("\n]}");
        return sb.toString();
    }
}



