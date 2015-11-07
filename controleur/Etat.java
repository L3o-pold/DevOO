package controleur;

public interface Etat {

    public void undo(ListeDeCommandes listeDeCommandes);
    public void redo(ListeDeCommandes listeDeCommandes);
}
