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
        this.livraisons = new ArrayList<>();
    }

    /*--- Accessors ---*/
	
	public List<Livraison> getLivraisons() {
		return livraisons;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}

    /*--- Public methods ---*/

	public void addLivraison(Livraison livraison) {
		livraisons.add(livraison);
	}

	public void removeLivraison(Livraison livraison) {
		livraisons.remove(livraison);
	}
}
