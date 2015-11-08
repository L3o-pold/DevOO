package controleur;

import controleur.commande.ListeDeCommandes;
import modele.Livrak;

public interface Etat {
    public void chargerPlan(Livrak app);
    public void chargerLivraisons(Livrak app);
    public void calculerTournee(Livrak app);
    public void undo(ListeDeCommandes listeDeCommandes);
    public void redo(ListeDeCommandes listeDeCommandes);
}
