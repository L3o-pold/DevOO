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

    /*--- Attributes ---*/

    private Plan plan;
    private Etat etatCourant;
    private ListeDeCommandes listeDeCommandes;

    /*--- Constructor ---*/

    public Controleur(Plan plan) {
        this.plan = plan;
        this.etatCourant = new EtatInit();
        listeDeCommandes = new ListeDeCommandes();
    }


    /*--- Public methods ---*/

    protected void setEtatCourant(Etat etat) {
        this.etatCourant = etat;
    }

    public void chargerPlan() {
        this.etatCourant.chargerPlan();
    }

    public void chargerLivraisons() {
        this.etatCourant.chargerLivraisons();
    }

    public void undo() {
        this.etatCourant.undo(listeDeCommandes);
    }

    public void redo() {
        this.etatCourant.redo(listeDeCommandes);
    }

}
