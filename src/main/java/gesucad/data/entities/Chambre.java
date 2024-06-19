package gesucad.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import gesucad.data.enums.TypeChambre;

@Data
@NoArgsConstructor
public class Chambre {
    private String numeroChambre;
    private int numeroEtage;
    private TypeChambre typeChambre;
    private Pavillon pavillon;

    public Chambre(String numeroChambre, int numeroEtage, TypeChambre typeChambre, Pavillon pavillon) {
        this.numeroChambre = numeroChambre;
        this.numeroEtage = numeroEtage;
        this.typeChambre = typeChambre;
        this.pavillon = pavillon;
    }

    public void delete() {
        this.numeroChambre = null;
        this.numeroEtage = 0;
        this.typeChambre = null;
        this.pavillon = null;
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "numeroChambre='" + numeroChambre + '\'' +
                ", numeroEtage=" + numeroEtage +
                ", typeChambre=" + typeChambre +
                ", pavillon=" + (pavillon != null ? pavillon.getNumero() : "null") +
                '}';
    }
}

