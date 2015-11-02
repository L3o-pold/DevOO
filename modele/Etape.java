package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Etape {

    /*--- Attributes ---*/

	private Date heurePassage;
	private double duree;
	private List<Troncon> troncons;

    /*--- Constructor ---*/

    public Etape(List<Troncon> troncons) {
        
    	this.duree = 0;
        for(Troncon t : troncons)
        {
        	this.addTroncon(t);
        }
    }
    
    public Etape() {
    	this.heurePassage = new Date();
    	this.duree = 0;
    	this.troncons = new ArrayList<Troncon>();
    }

    /*--- Accessors ---*/
	
	public Date getHeurePassage() {
		return heurePassage;
	}
	public void setHeurePassage(Date heurePassage) {
		this.heurePassage = heurePassage;
	}

	public double getDuree() {
		return duree;
	}
	public void setDuree(double duree) {
		this.duree = duree;
	}

	public List<Troncon> getTroncons() {
		return troncons;
	}
	public void setTroncons(List<Troncon> troncons) {
		this.troncons = troncons;
	}

    /*--- Public methods ---*/
	
	public void addTroncon(Troncon t)
	{
		troncons.add(t);
		this.setDuree(this.getDuree() + t.getDuree());
	}
	public void removeTroncon(Troncon t) {
		troncons.remove(t);
	}
}
