package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Plan {

    /*--- Attributes ---*/

	private int hauteur;
	private int largeur;
	private List<Intersection> intersections;


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
	public void setIntersections(List<Intersection> intersections) {
		this.intersections = intersections;
	}
	
	public void addIntersection(Intersection i)
	{
		this.intersections.add(i);
		if( i.getX() > this.getLargeur() )
		{
			this.setLargeur(i.getX());
		}
		if( i.getY() > this.getHauteur())
		{
			this.setHauteur(i.getY());
		}
	}
	
	public Intersection getIntersectionById(int id)
	{
		Iterator<Intersection> it = intersections.iterator();
		while(it.hasNext())
		{
			Intersection result = (Intersection) it.next();
			if(result.getId()==id)
			{
				return result;
			}
		}
		return null;
	}
	
}
