package modele;

public class Troncon {

	/*--- Attributes ---*/

	private double duree;
	private Intersection depart;
	private Intersection arrivee;

    /*--- Constructor ---*/

    public Troncon(double duree, Intersection depart, Intersection arrivee) {
        this.duree = duree;
        this.depart = depart;
        this.arrivee = arrivee;
    }
    
    public Troncon() {
    	
    }

    /*--- Accessors ---*/

	public double getDuree() {
		return duree;
	}
	public void setDuree(double duree) {
		this.duree = duree;
	}

	public Intersection getDepart() {
		return depart;
	}
	public void setDepart(Intersection depart) {
		this.depart = depart;
	}

	public Intersection getArrivee() {
		return arrivee;
	}
	public void setArrivee(Intersection arrivee) {
		this.arrivee = arrivee;
	}
}
