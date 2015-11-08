package controleur;


public class Controleur {

    /*--- Attributes ---*/
    
    private ListeDeCommandes listeDeCommandes;

    /*--- Constructor ---*/

    public Controleur() {
        listeDeCommandes = new ListeDeCommandes();
    }

    /*--- Public methods ---*/

    public void Undo() {
        listeDeCommandes.undo();
    }

    public void Redo() {
        listeDeCommandes.redo();
    }

}
