package controleur;

import modele.Livrak;

public class EtatPlanCharge extends EtatDefaut {

    /*--- Constructor ---*/

    public EtatPlanCharge() {

    }

    @Override
    public void chargerLivraisons(Livrak app) {
        app.chargerLivraisons();
        Controleur.setEtatCourant(Controleur.etatLivraisonChargees);
    }
}
