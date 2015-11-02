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
    	
    	//Fenetres
    	Date h1 = new Date(); Date h2 = new Date();
    	h1.setHours(8); h1.setMinutes(0);
    	h2.setHours(9); h2.setMinutes(30);
    	FenetreLivraison fl1 = new FenetreLivraison(h1,h2);
    	
    	h1.setHours(9); h1.setMinutes(30);
    	h2.setHours(11); h2.setMinutes(0);
    	FenetreLivraison fl2 = new FenetreLivraison(h1,h2);
    	
    	h1.setHours(11); h1.setMinutes(0);
    	h2.setHours(12); h2.setMinutes(30);
    	FenetreLivraison fl3 = new FenetreLivraison(h1,h2);
    	
    	ga.getPlan().addIntersection(i1);
    	
    }
    
}
