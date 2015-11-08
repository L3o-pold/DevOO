package controleur;

import modele.Livrak;

public class EtatLivraisonsChargees extends EtatDefaut {

    /*--- Constructor ---*/

    public EtatLivraisonsChargees() {

    }

    @Override
    public void calculerTournee(Livrak app) {
        app.calculerTournee();
        Controleur.setEtatCourant(Controleur.etatTourneeCalculee);
    }

}
