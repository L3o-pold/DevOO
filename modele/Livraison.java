package modele;

public class Livraison {

	/*--- Attributes ---*/

	private int id;
	private Intersection intersectionLivraison;
	
	/*--- Constructor ---*/

    public Livraison(int id, Intersection intersectionLivraison) {
        this.id = id;
        this.intersectionLivraison = intersectionLivraison;
    }

    /*--- Accessors ---*/

	public int getId() {
		return this.id;
	}

	public Intersection getIntersectionLivraison() {
		return intersectionLivraison;
	}
}
