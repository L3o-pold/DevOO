package controleur;

import java.util.LinkedList;
import java.util.List;

public class ListeDeCommandes {

    /*--- Attributes ---*/

    private List<Commande> commandes;
    private int index;


    /*--- Constructor ---*/

    public ListeDeCommandes() {
        this.commandes = new LinkedList<Commande>();
        this.index = 0;
    }

    /*--- Public methods ---*/

    public void add(Commande commande) {
        this.commandes.add(index, commande);
        this.index++;
    }

    public void undo() {
        if(index >= 0) {
            commandes.get(index--).unexecute();
        }
    }

    public void redo() {
        if(index < commandes.size() - 1) {
            commandes.get(++index).execute();
        }
    }
}
