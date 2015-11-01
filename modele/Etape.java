package modele;

import java.util.Date;
import java.util.List;

public class Etape {

    /*--- Attributes ---*/

	private Date heurePassage;
	private double duree;
	private List<Troncon> troncons;

    /*--- Constructor ---*/

    public Etape(Date heurePassage, double duree, List<Troncon> troncons) {
        this.heurePassage = heurePassage;
        this.duree = duree;
        this.troncons = troncons;
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
	}
	public void removeTroncon(Troncon t) {
		troncons.remove(t);
	}
}
