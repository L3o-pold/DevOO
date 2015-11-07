package modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Etape {

    /*--- Attributes ---*/

	private int idDepart;
	private int idArrivee;
	private List<Troncon> troncons;

    /*--- Constructor ---*/

    public Etape(int idDep, int idArr, List<Troncon> troncons) {
        this.idDepart = idDep;
        this.idArrivee = idArr;
        this.troncons = troncons;
    }

    /*--- Accessors ---*/

    public int getIdDepart() {
        return idDepart;
    }

    public int getIdArrivee() {
        return idArrivee;
    }

	public List<Troncon> getTroncons() {
		return troncons;
	}

    public double getDuree() {
        Double duree = 0.;

        for(Troncon troncon : this.troncons) {
            duree += troncon.getDuree();
        }

        return duree;
    }


	/*--- Public methods ---*/

	public void addTroncon(Troncon t) {
		this.troncons.add(t);
	}

	public void removeTroncon(Troncon t) {
		troncons.remove(t);
	}
}
