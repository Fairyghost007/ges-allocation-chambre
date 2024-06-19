package gesucad.services;

import java.util.ArrayList;
import java.util.List;

import gesucad.core.Service;
import gesucad.data.entities.Chambre;

public class ChambreServiceImpl  implements Service<Chambre>{

    List<Chambre> chambres = new ArrayList<>();

    @Override
    public boolean save(Chambre chambre) {
        chambres.add(chambre);
        return true;
    }

    @Override
    public List<Chambre> show() {
        return chambres;
        
    }

    @Override
    public Chambre getBy(String numeroChambre) {
        for (Chambre chambre : chambres) {
            if (chambre.getNumeroChambre().equals(numeroChambre)) {
                return chambre;
            }
        }
        return null;
    }

    @Override
    public int count() {
        return chambres.size();
    }
    

    

}
