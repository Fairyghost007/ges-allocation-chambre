package gesucad;

import java.util.Scanner;

import gesucad.core.Service;
import gesucad.core.View;
import gesucad.core.ViewImpl;
import gesucad.data.entities.Chambre;
import gesucad.data.entities.Etudiant;
import gesucad.data.entities.EtudiantBoursier;
import gesucad.data.entities.EtudiantNonBoursier;
import gesucad.data.entities.EtudiantBoursierLoge;
import gesucad.data.entities.Pavillon;
import gesucad.services.ChambreServiceImpl;
import gesucad.services.EtudiantServiceImpl;
import gesucad.services.PavillonServiceImpl;
import gesucad.views.ChambreView;
import gesucad.views.EtudiantView;
import gesucad.views.PavillonView;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Service<Pavillon> pavillonService = new PavillonServiceImpl();
        Service<Chambre> chambreService = new ChambreServiceImpl();
        Service<Etudiant> etudiantService = new EtudiantServiceImpl();

        ChambreView chambreView = new ChambreView(chambreService, pavillonService);
        PavillonView pavillonView = new PavillonView(pavillonService, chambreService, chambreView);
        EtudiantView etudiantView = new EtudiantView(etudiantService, chambreService);

        int choix;
        do {
            choix = menu();
            switch (choix) {
                case 1:
                    pavillonService.save(pavillonView.saisie());
                    break;
                case 2:
                    pavillonView.affiche(pavillonService.show());
                    break;
                case 3:
                    chambreService.save(chambreView.saisie());
                    break;
                case 4:
                    chambreView.affiche(chambreService.show());
                    break;
                case 5:
                    chambreView.delete();
                    break;
                case 6:
                    etudiantService.save(etudiantView.saisie());
                    break;
                case 7:
                    System.out.println("Entrer le numéro de l'étudiant boursier logé:\n-->");
                    String numeroEtudiant = scanner.next();
                    Etudiant etudiant = etudiantService.getBy(numeroEtudiant);
                    if (etudiant != null && etudiant instanceof EtudiantBoursierLoge) {
                        System.out.println("Entrer le numéro de la chambre:\n-->");
                        String numeroChambre = scanner.next();
                        Chambre chambre = chambreService.getBy(numeroChambre);
                        if (chambre != null) {
                            ((EtudiantBoursierLoge) etudiant).setChambre(chambre);
                            etudiantService.save(etudiant);
                        } else {
                            System.out.println("Chambre non trouvée.");
                        }
                    } else {
                        System.out.println("Étudiant non trouvé ou n'est pas un boursier logé.");
                    }
                    break;
                case 8:
                    System.out.println("Entrer le numéro du pavillon:\n-->");
                    String numeroPavillon = scanner.next();
                    Pavillon pavillon = pavillonService.getBy(numeroPavillon);
                    if (pavillon != null) {
                        chambreView.affiche(pavillon.getChambres());
                    } else {
                        System.out.println("Pavillon non trouvé.");
                    }
                    break;
                case 9:
                String numeroChambre;
                    System.out.println("Entrer le numéro de la chambre:\n-->");
                    numeroChambre = scanner.next();
                    Chambre chambre = chambreService.getBy(numeroChambre);
                    if (chambre != null) {
                        etudiantView.afficheEtudiantsDansChambre(chambre);
                    } else {
                        System.out.println("Chambre non trouvée.");
                    }
                    break;
                default:
                    break;
            }
        } while (choix != 10);
    }

    public static int menu() {
        System.out.println("1- Créer un pavillon:\n");
        System.out.println("2- Lister les pavillons:\n");
        System.out.println("3- Créer une chambre:\n");
        System.out.println("4- Lister les chambres:\n");
        System.out.println("5- Archiver les chambres:\n");
        System.out.println("6- Ajouter un étudiant:\n");
        System.out.println("7- Affecter une chambre à un boursier logé:\n");
        System.out.println("8- Lister les chambres d’un pavillon:\n");
        System.out.println("9- Lister les étudiants d’une chambre:\n");
        System.out.println("10- Quitter\n");
        System.out.println("Entrez votre choix:\n-->");
        return scanner.nextInt();
    }
}
