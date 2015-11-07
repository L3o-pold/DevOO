package modele;

import org.xml.sax.SAXException;
import xml.XMLOpener;
import xml.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Plan extends Observable {

    /*--- Attributes ---*/

    private int hauteur;
    private int largeur;
    private List<Intersection> intersections;
    private List<Troncon> troncons;
    private Intersection entrepot;

    private Tournee tournee;

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
        setChanged();
        notifyObservers();
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
        for (Intersection intersection : this.intersections) {
            if (intersection.getId() == id) {
                return intersection;
            }
        }
        return null;
    }

    public List<Troncon> getTroncons() {
        return this.troncons;
    }

    public Troncon getTronconByIntersections(Intersection depart, Intersection arrivee) {
        List<Troncon> troncons = this.getTroncons();

        for (Troncon troncon : troncons) {
            if (troncon.getDepart() == depart && troncon.getArrivee() == arrivee) {
                return troncon;
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

        if (depart != null && arrive != null) {
            this.troncons.add(new Troncon(duree, depart, arrive));
        }
    }

    public void addTroncon(Troncon troncon) {
        this.troncons.add(troncon);
    }

    /**
     * Utilise le moteur Dijkstra pour renvoyer le plus court chemin entre deux Intersections données
     *
     * @param depart  L'intersection de départ
     * @param arrivee L'intersection d'arrivée
     * @return Une liste de tronçons (Un chemin)
     */
    public List<Troncon> plusCourtChemin(Intersection depart, Intersection arrivee) {
        List<Troncon> troncons = new ArrayList<Troncon>();
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(this);
        dijkstra.execute(depart);
        //On récupère la liste d'intersections renvoyées par le moteur Dijkstra
        List<Intersection> chemin = dijkstra.getPath(arrivee);
        //On récupère les tronçons correspondant à la liste d'intersection
        for (int i = 0; i < chemin.size() - 1; i++) {
            troncons.add(this.getTronconByIntersections(chemin.get(i), chemin.get(i + 1)));
        }
        return troncons;
    }

    public void chargerPlan() {
        XMLOpener xmlOpener = XMLOpener.getInstance();

        try {
            File xmlFile = xmlOpener.open(false);
            if(xmlFile != null){
                XMLParser.chargerPlan(this, xmlFile);
                setChanged();
                notifyObservers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chargerLivraisons() {
        XMLOpener xmlOpener = XMLOpener.getInstance();

        try {
            File xmlFile = xmlOpener.open(false);
            if(xmlFile != null){
                //XMLParser.chargerLivraisons(this, xmlFile);
                setChanged();
                notifyObservers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
