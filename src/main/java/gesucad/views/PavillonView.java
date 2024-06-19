package gesucad.views;

import java.util.Scanner;
import gesucad.core.Service;
import gesucad.services.PavillonServiceImpl;
import gesucad.services.ChambreServiceImpl;
import gesucad.core.ViewImpl;
import gesucad.data.entities.Chambre;
import gesucad.data.entities.Pavillon;

public class PavillonView extends ViewImpl<Pavillon> {

    private Service<Pavillon> pavillonServiceImpl;
    private Service<Chambre> chambreServiceImpl;
    private ChambreView chambreView;

    public PavillonView(Service<Pavillon> pavillonServiceImpl, Service<Chambre> chambreServiceImpl, ChambreView chambreView) {
        this.pavillonServiceImpl = pavillonServiceImpl;
        this.chambreServiceImpl = chambreServiceImpl;
        this.chambreView = chambreView;
    }

    @Override
    public Pavillon saisie() {
        String numero;
        do {
            System.out.println("Entrer le numero de Pavillon:\n-->");
            numero = scanner.nextLine();
        } while (pavillonServiceImpl.getBy(numero) != null);

        Pavillon pavillon = new Pavillon();
        pavillon.setNumero(numero);
        System.out.println("Entrer le numero de l'Etage:\n-->");
        pavillon.setNumeroEtage(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Voulez-vous ajouter des chambres existantes (1) ou créer de nouvelles chambres (2) ?");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            ajouterChambresExistantes(pavillon);
        } else if (choice == 2) {
            creerNouvellesChambres(pavillon);
        }

        return pavillon;
    }

    private void ajouterChambresExistantes(Pavillon pavillon) {
        System.out.println("Entrer les numéros des chambres à ajouter, séparés par des virgules:");
        String[] numerosChambres = scanner.nextLine().split(",");
        for (String numero : numerosChambres) {
            Chambre chambre = chambreServiceImpl.getBy(numero.trim());
            if (chambre != null) {
                pavillon.ajouterChambre(chambre);
            } else {
                System.out.println("Chambre avec numéro " + numero.trim() + " non trouvée.");
            }
        }
    }

    private void creerNouvellesChambres(Pavillon pavillon) {
        String response;
        do {
            Chambre nouvelleChambre = chambreView.saisie();
            pavillon.ajouterChambre(nouvelleChambre);
            chambreServiceImpl.save(nouvelleChambre); 

            System.out.println("Voulez-vous ajouter une autre chambre ? (oui/non)");
            response = scanner.nextLine();
        } while (response.equalsIgnoreCase("oui"));
    }
}
