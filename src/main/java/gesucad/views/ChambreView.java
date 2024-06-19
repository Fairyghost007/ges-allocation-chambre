package gesucad.views;

import gesucad.core.Service;
import gesucad.core.ViewImpl;
import gesucad.data.entities.Chambre;
import gesucad.data.entities.Pavillon;
import gesucad.data.enums.TypeChambre;

public class ChambreView extends ViewImpl<Chambre> {

    private Service<Chambre> chambreServiceImpl;
    private Service<Pavillon> pavillonServiceImpl;

    public ChambreView(Service<Chambre> chambreServiceImpl, Service<Pavillon> pavillonServiceImpl) {
        this.chambreServiceImpl = chambreServiceImpl;
        this.pavillonServiceImpl = pavillonServiceImpl;
    }

    @Override
    public Chambre saisie() {
        String numeroChambre;
        do {
            System.out.println("Entrer le numero de Chambre:\n-->");
            numeroChambre = scanner.nextLine();
        } while (chambreServiceImpl.getBy(numeroChambre) != null);

        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(numeroChambre);
        System.out.println("Entrer le numero de l'Etage:\n-->");
        chambre.setNumeroEtage(scanner.nextInt());
        TypeChambre typeChambre = saisieTypeChambre();
        chambre.setTypeChambre(typeChambre);
        scanner.nextLine();

        System.out.println("Voulez-vous assigner cette chambre à un pavillon ? (oui/non)");
        String assignerPavillon = scanner.nextLine();
        if (assignerPavillon.equalsIgnoreCase("oui")) {
            System.out.println("Entrer le numero du pavillon:\n-->");
            String numeroPavillon = scanner.nextLine();
            Pavillon pavillon = pavillonServiceImpl.getBy(numeroPavillon);
            if (pavillon != null) {
                pavillon.ajouterChambre(chambre);
            } else {
                System.out.println("Pavillon non trouvé.");
            }
        }

        return chambre;
    }

    private TypeChambre saisieTypeChambre() {
        int typeChambreChoice;
        do {
            for (int i = 0; i < TypeChambre.values().length; i++) {
                System.out.println((i + 1) + "-" + TypeChambre.values()[i].name());
            }
            System.out.println("Veuillez sélectionner un Type de Chambre:");
            typeChambreChoice = scanner.nextInt();
        } while (typeChambreChoice <= 0 || typeChambreChoice > TypeChambre.values().length);

        return TypeChambre.values()[typeChambreChoice - 1];
    }

    public void delete() {
        System.out.println("Entrer le numéro de la chambre à archiver:\n-->");
        String numeroChambre = scanner.next();
        Chambre chambre = chambreServiceImpl.getBy(numeroChambre);
        if (chambre != null) {
            chambre.delete();
            System.out.println("Chambre archivée avec succès.");
        } else {
            System.out.println("Chambre non trouvée.");
        }
    }
}

