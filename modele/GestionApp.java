package modele;

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
    	Intersection i1 = new Intersection(0,63,100);
    	ga.getPlan().addIntersection(i1);
    	
    }
    
}
