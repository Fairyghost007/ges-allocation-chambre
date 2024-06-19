package gesucad.services;

import java.util.ArrayList;
import java.util.List;

import gesucad.core.Service;
import gesucad.data.entities.Etudiant;


public class EtudiantServiceImpl implements Service<Etudiant> {

    private List<Etudiant> etudiants = new ArrayList<>();

    @Override
    public boolean save(Etudiant etudiant) {
        return etudiants.add(etudiant);
    }

    @Override
    public List<Etudiant> show() {
        return new ArrayList<>(etudiants);
    }

    @Override
    public Etudiant getBy(String numero) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getNumero().equals(numero)) {
                return etudiant;
            }
        }
        return null;
    }

    @Override
    public int count() {
        return etudiants.size();
    }
}

