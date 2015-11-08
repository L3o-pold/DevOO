package controleur;

import controleur.commande.ListeDeCommandes;
import modele.Livrak;

public class EtatDefaut implements Etat {

    /*--- Constructor ---*/

    public EtatDefaut() {

    }

    /*--- Override methods ---*/

    @Override
    public void chargerPlan(Livrak app) {
        app.chargerPlan();
        Controleur.setEtatCourant(Controleur.etatPlanCharge);
    }

    @Override
    public void chargerLivraisons(Livrak app) {

    }

    @Override
    public void calculerTournee(Livrak app) {

    }

    @Override
    public void undo(ListeDeCommandes listeDeCommandes) {
        listeDeCommandes.undo();
    }

    @Override
    public void redo(ListeDeCommandes listeDeCommandes) {
        listeDeCommandes.redo();
    }
}
