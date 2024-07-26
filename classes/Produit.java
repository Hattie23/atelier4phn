package classes;


import java.io.Serializable;

public abstract class Produit implements Serializable {
    protected int id;
    protected String nom;
    protected int quantite;

    public Produit(int id, String nom, int quantite) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return String.format("Produit [id=%d, nom=%s, quantite=%d]", id, nom, quantite);
    }
}
