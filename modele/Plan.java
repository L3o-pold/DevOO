package modele;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    /*--- Attributes ---*/

	private int hauteur;
	private int largeur;
	private List<Intersection> intersections;
	private List<Troncon> troncons;


    /*--- Constructor ---*/

	public Plan() {
		this.hauteur = 0;
		this.largeur = 0;
		this.intersections = new ArrayList<Intersection>(); // Maybe another type of list
	}

    /*--- Accessors ---*/

	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public Intersection getIntersectionById(int id) {
        for(Intersection intersection : this.intersections) {
            if(intersection.getId() == id) {
                return intersection;
            }
        }
        return null;
    }

    public List<Troncon> getTroncons() {
        return this.troncons;
    }

    /*--- Public methods ---*/

    public void addIntersection(Intersection intersection) {
        this.intersections.add(intersection);
    }

	public void addIntersection(int id, int x, int y) {
        this.intersections.add(new Intersection(id, x, y));
    }

    public void addTroncon(double duree, int idDepart, int idArrive) {
        Intersection depart = getIntersectionById(idDepart);
        Intersection arrive = getIntersectionById(idArrive);

        if(depart != null && arrive != null) {
            this.troncons.add(new Troncon(duree, depart, arrive));
        }
    }

    public void addTroncon(Troncon troncon) {
        this.troncons.add(troncon);
    }
}
