package modele;

import java.util.ArrayList;
import java.util.List;

public class Plan {

    /*--- Attributes ---*/

	private int hauteur;
	private int largeur;
	private List<Intersection> intersections;


    /*--- Constructor ---*/

	public Plan(int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
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
	public void setIntersections(List<Intersection> intersections) {
		this.intersections = intersections;
	}
}
