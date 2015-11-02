package modele;

import java.util.Date;

public class GestionApp {

    /*--- Attributes ---*/

    private Plan plan;
    private Tournee tournee;
    

	public GestionApp() {
		this.plan = new Plan();
		this.tournee = new Tournee();
    }

    public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Tournee getTournee() {
		return tournee;
	}

	public void setTournee(Tournee tournee) {
		this.tournee = tournee;
	}

    
    /*--- Public methods ---*/
    public static void main(String [] args)
    {
        GestionApp ga = new GestionApp();
        
        //Intersections
    	Intersection i1 = new Intersection(0,63,100);
    	
    	//livraisons
    	Livraison l1 = new Livraison(ga.getPlan().getIntersectionById(3));
    	Livraison l2 = new Livraison(ga.getPlan().getIntersectionById(10));
    	Livraison l3 = new Livraison(ga.getPlan().getIntersectionById(15));
    	
    	Livraison l4 = new Livraison(ga.getPlan().getIntersectionById(4));
    	Livraison l5 = new Livraison(ga.getPlan().getIntersectionById(3));
    	Livraison l6 = new Livraison(ga.getPlan().getIntersectionById(15));
    	
    	Livraison l7 = new Livraison(ga.getPlan().getIntersectionById(7));
    	Livraison l8 = new Livraison(ga.getPlan().getIntersectionById(8));
    	Livraison l9 = new Livraison(ga.getPlan().getIntersectionById(12));
    	
    	
    	//Fenetres
    	Date h1 = new Date(); Date h2 = new Date();
    	h1.setHours(8); h1.setMinutes(0);
    	h2.setHours(9); h2.setMinutes(30);
    	FenetreLivraison fl1 = new FenetreLivraison(h1,h2);
    	fl1.addLivraison(l1); fl1.addLivraison(l2); fl1.addLivraison(l3);
    	
    	h1.setHours(9); h1.setMinutes(30);
    	h2.setHours(11); h2.setMinutes(0);
    	FenetreLivraison fl2 = new FenetreLivraison(h1,h2);
    	fl2.addLivraison(l4); fl2.addLivraison(l5); fl2.addLivraison(l6);
    	
    	h1.setHours(11); h1.setMinutes(0);
    	h2.setHours(12); h2.setMinutes(30);
    	FenetreLivraison fl3 = new FenetreLivraison(h1,h2);
    	fl3.addLivraison(l7); fl3.addLivraison(l8); fl3.addLivraison(l9);
    	
    	ga.getPlan().addIntersection(i1);
    	
    }
    
}
