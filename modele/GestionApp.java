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

        Intersection i0 = new Intersection(0,63,100);
        Intersection i1 = new Intersection(1,88,171);
        Intersection i2 = new Intersection(2,103,248);
        Intersection i3 = new Intersection(3,65,310);
        Intersection i4 = new Intersection(4,77,350);
        Intersection i5 = new Intersection(5,83,403);
        Intersection i6 = new Intersection(6,87,843);
        Intersection i7 = new Intersection(7,45,579);
        Intersection i8 = new Intersection(8,43,619);
        Intersection i9 = new Intersection(9,59,699);
        Intersection i10 = new Intersection(10,154,95);

        //Troncons
        

        ga.getPlan().addIntersection(i0);
        ga.getPlan().addIntersection(i1);
        ga.getPlan().addIntersection(i2);
        ga.getPlan().addIntersection(i3);
        ga.getPlan().addIntersection(i4);
        ga.getPlan().addIntersection(i5);
        ga.getPlan().addIntersection(i6);
        ga.getPlan().addIntersection(i7);
        ga.getPlan().addIntersection(i8);
        ga.getPlan().addIntersection(i9);
        ga.getPlan().addIntersection(i10);
        
        ga.getPlan().addTroncon(10,0,1);
        ga.getPlan().addTroncon(10,0,2);
        ga.getPlan().addTroncon(10,1,0);
        ga.getPlan().addTroncon(10,1,3);
        ga.getPlan().addTroncon(10,2,0);
        ga.getPlan().addTroncon(10,2,3);
        ga.getPlan().addTroncon(10,2,4);
        ga.getPlan().addTroncon(10,3,2);
        ga.getPlan().addTroncon(10,3,1);
        ga.getPlan().addTroncon(10,3,7);
        ga.getPlan().addTroncon(10,4,5);
        ga.getPlan().addTroncon(10,4,2);
        ga.getPlan().addTroncon(10,5,4);
        ga.getPlan().addTroncon(10,5,6);
        ga.getPlan().addTroncon(10,6,5);
        ga.getPlan().addTroncon(10,6,7);
        ga.getPlan().addTroncon(10,7,6);
        ga.getPlan().addTroncon(10,7,8);
        ga.getPlan().addTroncon(10,7,3);
        ga.getPlan().addTroncon(10,8,9);
        ga.getPlan().addTroncon(10,8,7);
        ga.getPlan().addTroncon(10,9,8);
        ga.getPlan().addTroncon(10,9,10);
        ga.getPlan().addTroncon(10,10,8);
        ga.getPlan().addTroncon(10,10,9);
        
        
        
        ga.getPlan().setEntrepot(i0);
    	
    	//livraisons
    	Livraison l1 = new Livraison(1, ga.getPlan().getIntersectionById(1));
    	Livraison l2 = new Livraison(2, ga.getPlan().getIntersectionById(2));
    	Livraison l3 = new Livraison(3, ga.getPlan().getIntersectionById(3));
    	
    	Livraison l4 = new Livraison(4, ga.getPlan().getIntersectionById(4));
    	Livraison l5 = new Livraison(5, ga.getPlan().getIntersectionById(5));
    	Livraison l6 = new Livraison(6, ga.getPlan().getIntersectionById(6));
    	
    	Livraison l7 = new Livraison(7, ga.getPlan().getIntersectionById(7));
    	Livraison l8 = new Livraison(8, ga.getPlan().getIntersectionById(8));
    	Livraison l9 = new Livraison(9, ga.getPlan().getIntersectionById(10));
    	
    	
    	//Fenetres
    	Date h1 = new Date(); Date h2 = new Date();
    	h1.setHours(8); h1.setMinutes(0);
    	h2.setHours(9); h2.setMinutes(30);
    	FenetreLivraison fl1 = new FenetreLivraison(h1,h2);
    	fl1.addLivraison(l1); fl1.addLivraison(l2); ;
    	ga.getTournee().addFenetre(fl1);
    	
    	h1.setHours(9); h1.setMinutes(30);
    	h2.setHours(11); h2.setMinutes(0);
    	FenetreLivraison fl2 = new FenetreLivraison(h1,h2);
    	fl2.addLivraison(l4); fl2.addLivraison(l5); fl2.addLivraison(l6);
    	fl2.addLivraison(l3);
    	ga.getTournee().addFenetre(fl2);
    	
    	h1.setHours(11); h1.setMinutes(0);
    	h2.setHours(12); h2.setMinutes(30);
    	FenetreLivraison fl3 = new FenetreLivraison(h1,h2);
    	fl3.addLivraison(l7); fl3.addLivraison(l8); fl3.addLivraison(l9);
    	ga.getTournee().addFenetre(fl3);
    
   
    	ga.getTournee().calculTournee(ga.getPlan());
    	
    }
    
}
