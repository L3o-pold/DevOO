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

    /*--- Accessors ---*/

	public double getDuree() {
		return duree;
	}

	public Intersection getDepart() {
		return depart;
	}

	public Intersection getArrivee() {
		return arrivee;
	}
}
