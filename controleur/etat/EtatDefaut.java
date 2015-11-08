package controleur.etat;

import controleur.commande.ListeDeCommandes;

public class EtatDefaut implements Etat {

    /*--- Constructor ---*/

    public EtatDefaut() {

    }

    /*--- Override methods ---*/

    @Override
    public void chargerPlan() {

    }

    @Override
    public void chargerLivraisons() {

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
