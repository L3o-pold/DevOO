package modele;

public class Livraison {

	/*--- Attributes ---*/

	private int ordre;
	private Intersection intersection;
	
	/*--- Constructor ---*/

    public Livraison(int o, Intersection intersection) {
    	this.ordre = o;
        this.intersection = intersection;
    }

    /*--- Accessors ---*/
	
	public int getOrdre() {
		return ordre;
	}
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public Intersection getIntersection() {
		return intersection;
	}
}
