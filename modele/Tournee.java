package modele;

import java.util.ArrayList;
import java.util.Iterator;
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
    
    public void calculTournee(Plan p)
    {
    	Intersection entrepot = p.getEntrepot();
    	Iterator<FenetreLivraison> it = fenetres.iterator();
    	FenetreLivraison fl = (FenetreLivraison)it.next();
    	List<Livraison> livraisons1 = (ArrayList<Livraison>) fl.getLivraisons();
    	
    	for(Livraison l : livraisons1)
    	{
    		List<Troncon> listT1 = p.plusCourtChemin(entrepot, l.getIntersectionLivraison());
    		Etape etape1 = new Etape(listT1);
    		for(Livraison l2 : livraisons1)
    		{
    			if(l != l2)
    			{
    				List<Troncon> listT2 = p.plusCourtChemin(l.getIntersectionLivraison(), l2.getIntersectionLivraison());
    				Etape etape2 = new Etape(listT2);
    			}
    		}
    	}
    	
    	while(it.hasNext())
    	{
    		//FenetreLivraison fl = (FenetreLivraison)it.next();
    		//ArrayList<Livraison> list = (ArrayList<Livraison>) fl.getLivraisons();
    		
    	}
    }
}
