package modele;

public class Livraison {

	/*--- Attributes ---*/

	private int ordre;
	private Intersection intersectionLivraison;
	
	/*--- Constructor ---*/

    public Livraison(int o, Intersection intersectionLivraison) {
    	this.ordre = o;
        this.intersectionLivraison = intersectionLivraison;
    }

    /*--- Accessors ---*/
	
	public int getOrdre() {
		return ordre;
	}
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public Intersection getIntersectionLivraison() {
		return intersectionLivraison;
	}
	public void setIntersectionLivraison(Intersection intersectionLivraison) {
		this.intersectionLivraison = intersectionLivraison;
	}
}
