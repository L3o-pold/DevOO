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

	public Intersection getIntersectionLivraison() {
		return intersectionLivraison;
	}
	public void setIntersectionLivraison(Intersection intersectionLivraison) {
		this.intersectionLivraison = intersectionLivraison;
	}
}
