package classes;


public class Vestimentaire extends Produit {
    private String taille;
    private String couleur;

    public Vestimentaire(int id, String nom, int quantite, String taille, String couleur) {
        super(id, nom, quantite);
        this.taille = taille;
        this.couleur = couleur;
    }

    public String getTaille() {
        return taille;
    }

    public String getCouleur() {
        return couleur;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", taille=%s, couleur=%s", taille, couleur);
    }
}
