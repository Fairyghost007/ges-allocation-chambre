package gesucad.services;

import java.util.ArrayList;
import java.util.List;
import gesucad.core.Service;
import gesucad.data.entities.Chambre;
import gesucad.data.entities.Pavillon;

public class PavillonServiceImpl implements Service<Pavillon> {

    List<Pavillon> pavillons = new ArrayList<>();

    @Override
    public boolean save(Pavillon pavillon) {
        pavillons.add(pavillon);
        return true;
    }

    @Override
    public List<Pavillon> show() {
        return pavillons;
    }

    @Override
    public Pavillon getBy(String numero) {
        for (Pavillon pavillon : pavillons) {
            if (pavillon.getNumero().equals(numero)) {
                return pavillon;
            }
        }
        return null;
    }

    @Override
    public int count() {
        return pavillons.size();
    }

    public void ajouterChambre(Pavillon pavillon, Chambre chambre) {
        for (Pavillon p : pavillons) {
            if (p.equals(pavillon)) {
                p.ajouterChambre(chambre);
                break;
            }
        }
    }
}
