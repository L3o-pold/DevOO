package modele;

public class Livraison {

	/*--- Attributes ---*/

	private int id;
	private Intersection intersection;
	private int ordre;
	
	/*--- Constructor ---*/

    public Livraison(int id, Intersection intersection) {
    	this.id = id;
        this.intersection = intersection;
		this.ordre = 0;
    }

    /*--- Accessors ---*/

    public int getId() {
        return id;
    }

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
