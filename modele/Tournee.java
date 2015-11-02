package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tournee {

    /*--- Attributes ---*/

    private List<Etape> etapes;
    private List<FenetreLivraison> fenetres;

    /*--- Constructor ---*/

    public Tournee() {
        // Maybe another type of list
        this.etapes = new ArrayList<>();
        this.fenetres = new ArrayList<>();
    }

    public Tournee(List<Etape> etapes, List<FenetreLivraison> fenetres) {
        this.etapes = etapes;
        this.fenetres = fenetres;
    }
    
    public Tournee() {
    	this.etapes = new ArrayList<Etape>();
    	this.fenetres = new ArrayList<FenetreLivraison>();
    }

    /*--- Accessors ---*/

    public List<Etape> getEtapes() {
        return etapes;
    }
    public List<FenetreLivraison> getFenetres() {
        return fenetres;
    }

    /*--- Public methods ---*/

    public void addFenetreLivraison(Date heureDebut, Date heureFin) {
        this.fenetres.add(new FenetreLivraison(heureDebut, heureFin));
    }

    public void addFenetreLivraison(FenetreLivraison fenetreLivraison) {
        this.fenetres.add(fenetreLivraison);
    }
}
