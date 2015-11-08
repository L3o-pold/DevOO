package controleur;


import controleur.commande.ListeDeCommandes;
import controleur.etat.*;
import modele.Plan;

public class Controleur {

    /*--- Static ---*/

    protected static final EtatInit etatInit = new EtatInit();
    protected static final EtatPlanCharge etatPlanCharge = new EtatPlanCharge();
    protected static final EtatLivraisonsChargees etatLivraisonChargees = new EtatLivraisonsChargees();
    protected static final EtatTourneeCalculee etatTourneeCalculee = new EtatTourneeCalculee();
    protected static final EtatAjouterLivraison etatAjouterLivraison = new EtatAjouterLivraison();
    protected static final EtatOrdreChoisi etatOrdreChoisi = new EtatOrdreChoisi();

    protected static Etat etatCourant = etatInit;

    protected static void setEtatCourant(Etat etat) {
        etatCourant = etat;
    }

    /*--- Attributes ---*/

    private Plan plan;
    private ListeDeCommandes listeDeCommandes;

    /*--- Constructor ---*/

    public Controleur(Plan plan) {
        this.plan = plan;
        listeDeCommandes = new ListeDeCommandes();
    }


    /*--- Public methods ---*/

    public void chargerPlan() {
        etatCourant.chargerPlan();
    }

    public void chargerLivraisons() {
        etatCourant.chargerLivraisons();
    }

    public void undo() {
        etatCourant.undo(this.listeDeCommandes);
    }

    public void redo() {
        etatCourant.redo(this.listeDeCommandes);
    }

}
