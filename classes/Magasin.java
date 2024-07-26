package classes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Magasin {
    private final Map<Integer, Produit> produits = new HashMap<>();
    private static final String FICHIER_MAGASIN = "magasin.txt";

    public void ajouterProduit(Produit produit) {
        produits.put(produit.getId(), produit);
        sauvegarderMagasin();
    }

    public void supprimerProduit(int id) {
        produits.remove(id);
        sauvegarderMagasin();
    }

    public void modifierProduit(int id, Produit nouveauProduit) {
        produits.put(id, nouveauProduit);
        sauvegarderMagasin();
    }

    public Produit rechercherProduitParNom(String nom) {
        return produits.values().stream()
                .filter(produit -> produit.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    public void listerProduitParLettre(char lettre) {
        produits.values().stream()
                .filter(produit -> produit.getNom().toLowerCase().startsWith(String.valueOf(lettre).toLowerCase()))
                .forEach(produit -> System.out.println(produit.getNom()));
    }

    public int afficherNombreProduits() {
        return produits.size();
    }

    public void afficherProduitParCategorie(Class<? extends Produit> categorie) {
        produits.values().stream()
                .filter(categorie::isInstance)
                .forEach(System.out::println);
    }

    public void afficherProduitsBassesQuantites(int seuilQuantite) {
        produits.values().stream()
                .filter(produit -> produit.getQuantite() < seuilQuantite)
                .forEach(produit -> System.out.println(produit.getNom() + " : " + produit.getQuantite() + " en stock"));
    }

    private void sauvegarderMagasin() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHIER_MAGASIN))) {
            oos.writeObject(produits);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du magasin: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void chargerMagasin() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHIER_MAGASIN))) {
            Map<Integer, Produit> produitsChargés = (Map<Integer, Produit>) ois.readObject();
            produits.putAll(produitsChargés);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement du magasin: " + e.getMessage());
        }
    }
}
