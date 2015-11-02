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
        Troncon t1 = new Troncon(10,i0,i1);
        Troncon t2 = new Troncon(10,i0,i2);
        i0.ajouterTroncon(t1);
        i0.ajouterTroncon(t2);

        Troncon t3 = new Troncon(10,i1,i0);
        Troncon t4 = new Troncon(10,i1,i3);
        i1.ajouterTroncon(t3);
        i1.ajouterTroncon(t4);

        Troncon t5 = new Troncon(10,i2,i0);
        Troncon t6 = new Troncon(10,i2,i3);
        Troncon t7 = new Troncon(10,i2,i4);
        i2.ajouterTroncon(t5);
        i2.ajouterTroncon(t6);
        i2.ajouterTroncon(t7);

        Troncon t8 = new Troncon(10,i3,i2);
        Troncon t9 = new Troncon(10,i3,i1);
        Troncon t10 = new Troncon(10,i3,i7);
        i3.ajouterTroncon(t8);
        i3.ajouterTroncon(t9);
        i3.ajouterTroncon(t10);

        Troncon t11 = new Troncon(10,i4,i5);
        Troncon t12 = new Troncon(10,i4,i2);
        i4.ajouterTroncon(t11);
        i4.ajouterTroncon(t12);

        Troncon t13 = new Troncon(10,i5,i4);
        Troncon t14 = new Troncon(10,i5,i6);
        i5.ajouterTroncon(t13);
        i5.ajouterTroncon(t14);

        Troncon t15 = new Troncon(10,i6,i5);
        Troncon t16 = new Troncon(10,i6,i7);
        i6.ajouterTroncon(t15);
        i6.ajouterTroncon(t16);

        Troncon t17 = new Troncon(10,i7,i6);
        Troncon t18 = new Troncon(10,i7,i8);
        Troncon t19 = new Troncon(10,i7,i3);
        i7.ajouterTroncon(t17);
        i7.ajouterTroncon(t18);
        i7.ajouterTroncon(t19);

        Troncon t20 = new Troncon(10,i8,i9);
        Troncon t21 = new Troncon(10,i8,i10);
        Troncon t22 = new Troncon(10,i8,i7);
        i8.ajouterTroncon(t20);
        i8.ajouterTroncon(t21);
        i8.ajouterTroncon(t22);

        Troncon t23 = new Troncon(10,i9,i8);
        Troncon t24 = new Troncon(10,i9,i10);
        i9.ajouterTroncon(t23);
        i9.ajouterTroncon(t24);

        Troncon t25 = new Troncon(10,i10,i8);
        Troncon t26 = new Troncon(10,i10,i9);
        i10.ajouterTroncon(t25);
        i10.ajouterTroncon(t26);

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
    	System.out.println(ga.getPlan().getHauteur());
    	
    }
    
}
