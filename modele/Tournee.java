package modele;

import java.util.ArrayList;
import java.util.List;

public class Tournee {

    /*--- Attributes ---*/

    private List<Etape> etapes;
    private List<FenetreLivraison> fenetres;

    /*--- Constructor ---*/

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
    public void setEtapes(List<Etape> etapes) {
        this.etapes = etapes;
    }

    public List<FenetreLivraison> getFenetres() {
        return fenetres;
    }
    public void setFenetres(List<FenetreLivraison> fenetres) {
        this.fenetres = fenetres;
    }
}
