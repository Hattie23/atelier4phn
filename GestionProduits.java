
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Magasin;
import classes.Vestimentaire;
import classes.Alimentaire;
import classes.Electronique;
import classes.Produit;


public class GestionProduits {

    public static void main(String[] args) {
        Magasin magasin = new Magasin();
        magasin.chargerMagasin();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choisissez une fonctionnalité :");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Supprimer un produit");
            System.out.println("3. Modifier un produit");
            System.out.println("4. Rechercher un produit par nom");
            System.out.println("5. Lister les produits par lettre");
            System.out.println("6. Afficher le nombre de produits en stock");
            System.out.println("7. Afficher les produits par catégorie");
            System.out.println("8. Afficher les produits avec faible quantité");
            System.out.println("9. Quitter");

            int choix = 0;
            try {
                choix = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Entrée invalide, veuillez entrer un numéro.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }
            scanner.nextLine(); // Consommer la ligne vide

            try {
                switch (choix) {
                    case 1:
                        ajouterProduit(magasin, scanner);
                        break;
                    case 2:
                        supprimerProduit(magasin, scanner);
                        break;
                    case 3:
                        modifierProduit(magasin, scanner);
                        break;
                    case 4:
                        rechercherProduitParNom(magasin, scanner);
                        break;
                    case 5:
                        listerProduitParLettre(magasin, scanner);
                        break;
                    case 6:
                        afficherNombreProduits(magasin);
                        break;
                    case 7:
                        afficherProduitParCategorie(magasin);
                        break;
                    case 8:
                        afficherProduitsBassesQuantites(magasin, scanner);
                        break;
                    case 9:
                        System.out.println("Au revoir !");
                        return;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erreur lors de l'exécution de la commande : " + e.getMessage());
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private static void ajouterProduit(Magasin magasin, Scanner scanner) {
        try {
            System.out.print("Entrez le type de produit (Electronique, Alimentaire, Vestimentaire) : ");
            String type = scanner.nextLine();
            System.out.print("Entrez l'ID du produit : ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Entrez le nom du produit : ");
            String nom = scanner.nextLine();
            System.out.print("Entrez la quantité en stock : ");
            int quantite = scanner.nextInt();
            scanner.nextLine();
            Produit newProduit;
            switch (type) {
                case "Electronique":
                    System.out.print("Entrez le type d'électronique : ");
                    String typeElec = scanner.nextLine();
                    System.out.print("Entrez la marque : ");
                    String marque = scanner.nextLine();
                    newProduit = new Electronique(id, nom, quantite, typeElec, marque);
                    break;
                case "Alimentaire":
                    System.out.print("Entrez la catégorie alimentaire : ");
                    String categorie = scanner.nextLine();
                    System.out.print("Entrez la date d'expiration : ");
                    String dateExpiration = scanner.nextLine();
                    newProduit = new Alimentaire(id, nom, quantite, categorie, dateExpiration);
                    break;
                case "Vestimentaire":
                    System.out.print("Entrez la taille : ");
                    String taille = scanner.nextLine();
                    System.out.print("Entrez la couleur : ");
                    String couleur = scanner.nextLine();
                    newProduit = new Vestimentaire(id, nom, quantite, taille, couleur);
                    break;
                default:
                    System.out.println("Type de produit invalide.");
                    return;
            }
            magasin.ajouterProduit(newProduit);
            System.out.println("Produit ajouté avec succès.");
        } catch (InputMismatchException e) {
            System.err.println("Erreur de saisie : " + e.getMessage());
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void supprimerProduit(Magasin magasin, Scanner scanner) {
        try {
            System.out.print("Entrez l'ID du produit à supprimer : ");
            int id = scanner.nextInt();
            scanner.nextLine();
            magasin.supprimerProduit(id);
            System.out.println("Produit supprimé avec succès.");
        } catch (InputMismatchException e) {
            System.err.println("Erreur de saisie : " + e.getMessage());
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void modifierProduit(Magasin magasin, Scanner scanner) {
        try {
            System.out.print("Entrez l'ID du produit à modifier : ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Entrez le nouveau nom du produit : ");
            String nouveauNom = scanner.nextLine();
            System.out.print("Entrez la nouvelle quantité en stock : ");
            int nouvelleQuantite = scanner.nextInt();
            scanner.nextLine();
            if (!magasin.produits.containsKey(id)) {
                System.out.println("Produit non trouvé.");
                return;
            }
            Produit produitExistant = magasin.produits.get(id);
            Produit produitModifie;
            if (produitExistant instanceof Electronique) {
                System.out.print("Entrez le nouveau type d'électronique : ");
                String typeElec = scanner.nextLine();
                System.out.print("Entrez la nouvelle marque : ");
                String marque = scanner.nextLine();
                produitModifie = new Electronique(id, nouveauNom, nouvelleQuantite, typeElec, marque);
            } else if (produitExistant instanceof Alimentaire) {
                System.out.print("Entrez la nouvelle catégorie alimentaire : ");
                String categorie = scanner.nextLine();
                System.out.print("Entrez la nouvelle date d'expiration : ");
                String dateExpiration = scanner.nextLine();
                produitModifie = new Alimentaire(id, nouveauNom, nouvelleQuantite, categorie, dateExpiration);
            } else if (produitExistant instanceof Vestimentaire) {
                System.out.print("Entrez la nouvelle taille : ");
                String taille = scanner.nextLine();
                System.out.print("Entrez la nouvelle couleur : ");
                String couleur = scanner.nextLine();
                produitModifie = new Vestimentaire(id, nouveauNom, nouvelleQuantite, taille, couleur);
            } else {
                System.out.println("Type de produit invalide.");
                return;
            }
            magasin.modifierProduit(id, produitModifie);
            System.out.println("Produit modifié avec succès.");
        }         } catch (InputMismatchException e) {
            System.err.println("Erreur de saisie : " + e.getMessage());
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static void rechercherProduitParNom(Magasin magasin, Scanner scanner) {
        System.out.print("Entrez le nom du produit à rechercher : ");
        String nomRecherche = scanner.nextLine();
        Produit produitTrouve = magasin.rechercherProduitParNom(nomRecherche);
        if (produitTrouve != null) {
            System.out.println("Produit trouvé : " + produitTrouve);
        } else {
            System.out.println("Produit non trouvé.");
        }
    }

    private static void listerProduitParLettre(Magasin magasin, Scanner scanner) {
        System.out.print("Entrez la lettre pour lister les produits : ");
        char lettre = scanner.nextLine().charAt(0);
        System.out.println("Produits commençant par la lettre " + lettre + " :");
        magasin.listerProduitParLettre(lettre);
    }

    private static void afficherNombreProduits(Magasin magasin) {
        int nombreProduits = magasin.afficherNombreProduits();
        System.out.println("Nombre total de produits en stock : " + nombreProduits);
    }

    private static void afficherProduitParCategorie(Magasin magasin) {
        System.out.println("Produits par catégorie :");
        System.out.println("Électronique :");
        magasin.afficherProduitParCategorie(Electronique.class);
        System.out.println("Alimentaire :");
        magasin.afficherProduitParCategorie(Alimentaire.class);
        System.out.println("Vestimentaire :");
        magasin.afficherProduitParCategorie(Vestimentaire.class);
    }

    private static void afficherProduitsBassesQuantites(Magasin magasin, Scanner scanner) {
        try {
            System.out.print("Entrez le seuil de quantité minimum : ");
            int seuilQuantite = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Produits avec quantité inférieure à " + seuilQuantite + " :");
            magasin.afficherProduitsBassesQuantites(seuilQuantite);
        } catch (InputMismatchException e) {
            System.err.println("Erreur de saisie : " + e.getMessage());
            scanner.nextLine(); // Clear the invalid input
        }
    }
}

