package gesucad.views;

import gesucad.core.Service;
import gesucad.core.ViewImpl;
import gesucad.data.entities.*;
import gesucad.data.enums.TypeBourse;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EtudiantView extends ViewImpl<Etudiant> {

    private Service<Etudiant> etudiantService;
    private Service<Chambre> chambreService;

    public EtudiantView(Service<Etudiant> etudiantService, Service<Chambre> chambreService) {
        this.etudiantService = etudiantService;
        this.chambreService = chambreService;
    }

    @Override
    public Etudiant saisie() {
        String numero;
        do {
            System.out.println("Entrer le numero de l'étudiant:\n-->");
            numero = scanner.nextLine();
        } while (etudiantService.getBy(numero) != null);
        System.out.println("Entrer le nom de l'étudiant:\n-->");
        String nom = scanner.nextLine();
        System.out.println("Entrer le prenom de l'étudiant:\n-->");
        String prenom = scanner.nextLine();
        System.out.println("Entrer l'email de l'étudiant:\n-->");
        String email = scanner.nextLine();
        System.out.println("Entrer le numéro de téléphone de l'étudiant:\n-->");
        String telephone = scanner.nextLine();
        System.out.println("Entrer la date de naissance (format yyyy-MM-dd):\n-->");
        Date dateNaissance = java.sql.Date.valueOf(scanner.nextLine());

        System.out.println("Sélectionner le type d'étudiant:\n1- Boursier\n2- Non Boursier\n3- Boursier Logé");
        int typeEtudiant = scanner.nextInt();
        scanner.nextLine();

        Etudiant etudiant = null;

        switch (typeEtudiant) {
            case 1:
                etudiant = saisieEtudiantBoursier(numero, nom, prenom, email, telephone, dateNaissance);
                break;
            case 2:
                etudiant = saisieEtudiantNonBoursier(numero, nom, prenom, email, telephone, dateNaissance);
                break;
            case 3:
                etudiant = saisieEtudiantBoursierLoge(numero, nom, prenom, email, telephone, dateNaissance);
                break;
            default:
                System.out.println("Type d'étudiant non valide");
                break;
        }

        return etudiant;
    }

    public void afficheEtudiantsDansChambre(Chambre chambre) {
        List<Etudiant> etudiants = etudiantService.show();
        System.out.println("Étudiants dans la chambre " + chambre.getNumeroChambre() + ":");
        boolean found = false;
        for (Etudiant etudiant : etudiants) {
            if (etudiant instanceof EtudiantBoursierLoge) {
                EtudiantBoursierLoge etudiantBoursierLoge = (EtudiantBoursierLoge) etudiant;
                if (etudiantBoursierLoge.getChambre().equals(chambre)) {
                    System.out.println(etudiant);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Aucun étudiant trouvé dans cette chambre.");
        }
    }

    private EtudiantBoursier saisieEtudiantBoursier(String numero, String nom, String prenom, String email, String telephone, Date dateNaissance) {
        TypeBourse typeBourse = saisieTypeBourse();
        return new EtudiantBoursier(numero, nom, prenom, email, telephone, dateNaissance, typeBourse);
    }

    private EtudiantNonBoursier saisieEtudiantNonBoursier(String numero, String nom, String prenom, String email, String telephone, Date dateNaissance) {
        System.out.println("Entrer l'adresse de l'étudiant:\n-->");
        String adresse = scanner.nextLine();
        return new EtudiantNonBoursier(numero, nom, prenom, email, telephone, dateNaissance, adresse);
    }

    private EtudiantBoursierLoge saisieEtudiantBoursierLoge(String numero, String nom, String prenom, String email, String telephone, Date dateNaissance) {
        TypeBourse typeBourse = saisieTypeBourse();
        System.out.println("Entrer le numéro de la chambre:\n-->");
        String numeroChambre = scanner.nextLine();
        Chambre chambre = chambreService.getBy(numeroChambre);
        if (chambre == null) {
            System.out.println("Chambre non trouvée.");
            return null;
        }
        return new EtudiantBoursierLoge(numero, nom, prenom, email, telephone, dateNaissance, typeBourse, chambre);
    }

    private TypeBourse saisieTypeBourse() {
        int typeBourseChoice;
        do {
            for (TypeBourse typeBourse : TypeBourse.values()) {
                System.out.println((typeBourse.ordinal() + 1) + "-" + typeBourse.name());
            }
            System.out.println("Veuillez sélectionner un type de bourse ");
            typeBourseChoice = scanner.nextInt();
        } while (typeBourseChoice <= 0 || typeBourseChoice > TypeBourse.values().length);

        return TypeBourse.values()[typeBourseChoice - 1];
    }
}
