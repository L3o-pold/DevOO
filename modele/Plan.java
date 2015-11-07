package modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Plan {

    /*--- Attributes ---*/

	private int hauteur;
	private int largeur;
	private List<Intersection> intersections;
	private List<Troncon> troncons;
	private Intersection entrepot;

    /*--- Constructor ---*/

	public Plan(int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
        // Maybe another type of list
		this.intersections = new ArrayList<>();
		this.troncons = new ArrayList<>();
	}
	

    /*--- Accessors ---*/

	public Intersection getEntrepot() {
		return entrepot;
	}


	public void setEntrepot(Intersection entrepot) {
		this.entrepot = entrepot;
	}


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
    
    public List<Troncon> plusCourtChemin(Intersection depart, Intersection arrivee)
    {
    	List<Troncon> troncons = new ArrayList<Troncon>();
    	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(this);
    	dijkstra.execute(depart);
    	LinkedList<Intersection> chemin = dijkstra.getPath(arrivee);
    	System.out.println("Chemin entre intersection " + depart.getId() + " et intersection " + arrivee.getId());
    	for(int i = 0; i < chemin.size() ; i++)
    	{
    		if(chemin.get(i+1) != null)
    		{
    			
    		}
    	}
    	
    	
    	return troncons;
    }
}