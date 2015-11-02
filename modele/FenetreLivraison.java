package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FenetreLivraison {

	/*--- Attributes ---*/
	
	private List<Livraison> livraisons;
	private Date heureDebut;
	private Date heureFin;

    /*--- Constructor ---*/

    public FenetreLivraison(Date heureDebut, Date heureFin) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        livraisons = new ArrayList<Livraison>();
    }
    
    public FenetreLivraison() {
        this.heureDebut = new Date();
        this.heureFin = new Date();
        livraisons = new ArrayList<Livraison>();
    }

    /*--- Accessors ---*/
	
	public List<Livraison> getLivraisons() {
		return livraisons;
	}
	public void setLivraisons(List<Livraison> livraisons) {
		this.livraisons = livraisons;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

    /*--- Public methods ---*/

	public void addLivraison(Livraison l)
	{
		livraisons.add(l);
	}
	public void removeLivraison(Livraison l)
	{
		livraisons.remove(l);
	}
}
