package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Etape {

    /*--- Attributes ---*/
	private int idDepart;
	private int idArrivee;
	private Date heurePassage;
	private double duree;
	private List<Troncon> troncons;

    /*--- Constructor ---*/

    public Etape(int idDep, int idArr, List<Troncon> troncons) {
        this.idDepart = idDep;
        this.idArrivee = idArr;
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
	
	
	public int getIdDepart() {
		return idDepart;
	}

	public void setIdDepart(int idDepart) {
		this.idDepart = idDepart;
	}

	public int getIdArrivee() {
		return idArrivee;
	}

	public void setIdArrivee(int idArrivee) {
		this.idArrivee = idArrivee;
	}
	
	/*--- Public methods ---*/

	public void addTroncon(Troncon t)
	{
		this.troncons.add(t);
		this.setDuree(this.getDuree() + t.getDuree());
	}
	public void removeTroncon(Troncon t) {
		troncons.remove(t);
	}
}
