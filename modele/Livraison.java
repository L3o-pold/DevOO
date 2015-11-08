package modele;

public class Livraison {

	/*-- Static ---*/

	private static int nombreLivraisons = 1;

	/*--- Attributes ---*/

	private int id;
	private Intersection intersection;
	private int numero;
	
	/*--- Constructor ---*/

    public Livraison(int id, Intersection intersection) {
    	this.id = id;
        this.intersection = intersection;
		this.numero = Livraison.nombreLivraisons;
		Livraison.nombreLivraisons++;
    }

    /*--- Accessors ---*/

    public int getId() {
        return id;
    }

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Intersection getIntersection() {
		return intersection;
	}
}
