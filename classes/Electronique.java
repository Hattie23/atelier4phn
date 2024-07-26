package classes;


public class Electronique extends Produit {
    private String type;
    private String marque;

    public Electronique(int id, String nom, int quantite, String type, String marque) {
        super(id, nom, quantite);
        this.type = type;
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public String getMarque() {
        return marque;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", type=%s, marque=%s", type, marque);
    }
}
