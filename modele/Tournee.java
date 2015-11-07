package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tsp.GrapheEtapes;
import tsp.TSP;
import tsp.TSP1;

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
    
    public void addEtape(Etape e)
    {
    	this.etapes.add(e);
    }
    
    public void addFenetre(FenetreLivraison fl)
    {
    	this.fenetres.add(fl);
    }
    
    public void removeFenetre(FenetreLivraison fl)
    {
    	this.fenetres.remove(fl);
    }
    
    /**
     * Permet de compter le nombre de livraisons à faire, afin notamment d'instancier le graphe  
     * @return le nombre de livraisons
     */
    public int getNbLivraisons()
    {
    	int cpt=0;
    	for(FenetreLivraison fl : this.fenetres)
    	{
    		for(Livraison l : fl.getLivraisons())
    		{
    			cpt++;
    		}
    	}
    	return cpt;
    }
    /**
     * Permet de calculer la tournée
     * @param p le plan contenant l'ensemble des intersections et les méthodes de calcul de chemin le plus court
     */
    public void calculTournee(Plan p)
    {
    	//System.out.println(this.getNbLivraisons());
    	//On rajoute 1 car l'entrepot est un sommet supplémentaire du graphe (Et pas une livraison)
    	GrapheEtapes graphe= new GrapheEtapes(this.getNbLivraisons() + 1);
    	
    	//Instanciations    	
    	List<Troncon> plusCourtChemin = new ArrayList<Troncon>();
    	Etape etape = new Etape();
    	List<Etape> etapes = new ArrayList<Etape>();
    	
    	//On récupère l'entrepot et les livraisons de la première fenêtre de livraison
    	Intersection entrepot = p.getEntrepot();

    	FenetreLivraison fenetreLivraison1 = new FenetreLivraison();
    	FenetreLivraison fenetreLivraison2 = new FenetreLivraison();
    	Iterator<FenetreLivraison> it = fenetres.iterator();
    	fenetreLivraison1 = it.next();
    	List<Livraison> livraisons1 = new ArrayList<Livraison>();
    	List<Livraison> livraisons2 = new ArrayList<Livraison>();
    	livraisons1 = fenetreLivraison1.getLivraisons();
    	
    	
    	for(Livraison l : livraisons1)
    	{
    		//On calcule tous les plus courts chemins entre l'entrepot et les livraison
    		plusCourtChemin = p.plusCourtChemin(entrepot, l.getIntersectionLivraison());
    		etape = new Etape(0 , l.getOrdre() ,plusCourtChemin);
    		graphe.ajouterEtape(etape);
    		etapes.add(etape);
    		for(Livraison l2 : livraisons1)
    		{
    			if(l != l2)
    			{
    				//On calcule ici tous les plus courts chemins entre chaque livraison de la fenetre
    				plusCourtChemin = p.plusCourtChemin(l.getIntersectionLivraison(), l2.getIntersectionLivraison());
    				etape = new Etape(l.getOrdre(),l2.getOrdre(), plusCourtChemin);
    				graphe.ajouterEtape(etape);
    				etapes.add(etape);
    			}
    		}
    	}
    	
    	//On passe ensuite aux autres fenêtres de livraisons
    	while(it.hasNext())
    	{
    		fenetreLivraison2 = (FenetreLivraison)it.next();
    		livraisons2 = fenetreLivraison2.getLivraisons();
    		for(Livraison l : livraisons1)
    		{
    			for(Livraison l2 : livraisons2)
    			{
    				//Plus court chemin entre les livraisons de la fenetre précédente et celle en cours
    				plusCourtChemin = p.plusCourtChemin(l.getIntersectionLivraison(), l2.getIntersectionLivraison());
    				etape = new Etape(l.getOrdre(),l2.getOrdre(), plusCourtChemin);
    				graphe.ajouterEtape(etape);
    				etapes.add(etape);
    				for(Livraison l3 : livraisons2)
    				{
    					if(l2 != l3)
    					{
    						//Plus court chemin entre les livraisons de la même fenêtre
    						plusCourtChemin = p.plusCourtChemin(l2.getIntersectionLivraison(), l3.getIntersectionLivraison());
    						etape = new Etape(l2.getOrdre(),l3.getOrdre(), plusCourtChemin);
    						graphe.ajouterEtape(etape);
    	    				etapes.add(etape);
    					}
    				}
    			}
    		}
    		fenetreLivraison1 = fenetreLivraison2;
    		livraisons1 = livraisons2;
    	}
    	
    	//Retour à l'entrepot
    	for(Livraison l : livraisons1)
    	{
    		plusCourtChemin = p.plusCourtChemin(l.getIntersectionLivraison(), entrepot);
    		etape = new Etape(l.getOrdre(),0, plusCourtChemin);
    		graphe.ajouterEtape(etape);
    		etapes.add(etape);
    	}
    	
    	int cpt=0;
    	
    	for(Etape e : etapes)
    	{
    		cpt++;
    		System.out.println(e.getDuree());
    	}
    	
    	//TODO : ENVOYER GRAPHE AU TSP, FAIRE LA TOURNEE (PRENDRE LES BONNES ETAPES ET LES ORDONNER)
    	/*TSP tsp = new TSP1();
		for (int nbSommets = 8; nbSommets <= 16; nbSommets += 2){
			System.out.println("Graphes de "+nbSommets+" sommets :");
			long tempsDebut = System.currentTimeMillis();
			tsp.chercheSolution(60000, graphe);
			System.out.print("Solution de longueur "+tsp.getCoutSolution()+" trouvee en "
					+(System.currentTimeMillis() - tempsDebut)+"ms : ");
			for (int i=0; i<nbSommets; i++)
				System.out.print(tsp.getSolution(i)+" ");
			System.out.println();
		}*/
    	
    	
    }
}
