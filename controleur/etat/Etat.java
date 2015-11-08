package controleur.etat;

import controleur.commande.ListeDeCommandes;

public interface Etat {
    public void chargerPlan();
    public void chargerLivraisons();
    public void undo(ListeDeCommandes listeDeCommandes);
    public void redo(ListeDeCommandes listeDeCommandes);
}