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

	public Plan() {
		this.hauteur = 0;
		this.largeur = 0;
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
    
    public Troncon getTronconByIntersections(Intersection depart, Intersection arrivee)
    {
    	List<Troncon> troncons = this.getTroncons();
    	for(int i=0 ; i< troncons.size() ; i++)
    	{
    		if(troncons.get(i).getDepart() == depart && troncons.get(i).getArrivee() == arrivee)
    		{
    			return troncons.get(i);
    		}
    	}
    	return null;
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
    /**
     *  Utilise le moteur Dijkstra pour renvoyer le plus court chemin entre deux Intersections donn�es
     * @param depart L'intersection de d�part
     * @param arrivee L'intersection d'arriv�e
     * @return Une liste de tron�ons (Un chemin)
     */
    public List<Troncon> plusCourtChemin(Intersection depart, Intersection arrivee)
    {
    	List<Troncon> troncons = new ArrayList<Troncon>();
    	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(this);
    	dijkstra.execute(depart);
    	//On r�cup�re la liste d'intersections renvoy�es par le moteur Dijkstra
    	LinkedList<Intersection> chemin = dijkstra.getPath(arrivee);
    	Troncon t = new Troncon();
    	//On r�cup�re les tron�ons correspondant � la liste d'intersection
    	for(int i = 0; i < chemin.size()-1 ; i++)
    	{
    			t = this.getTronconByIntersections(chemin.get(i), chemin.get(i+1));
    			troncons.add(t);
    	}
    	return troncons;
    }
