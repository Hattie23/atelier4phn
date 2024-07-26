package classes;


public class Alimentaire extends Produit {
    private String categorie;
    private String dateExpiration;

    public Alimentaire(int id, String nom, int quantite, String categorie, String dateExpiration) {
        super(id, nom, quantite);
        this.categorie = categorie;
        this.dateExpiration = dateExpiration;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", categorie=%s, dateExpiration=%s", categorie, dateExpiration);
    }
}
