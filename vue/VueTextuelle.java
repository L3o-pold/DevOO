package vue;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import modele.FenetreLivraison;
import modele.Tournee;

public class VueTextuelle extends JList implements Observer{

	
	private FenetreLivraison[] fenetres;
	private ListModel model;
	
	public VueTextuelle() {
		model = new AbstractListModel() {

			@Override
			public Object getElementAt(int i) {
				return " Livraison " + fenetres[i];
			}

			@Override
			public int getSize() {
				return fenetres.length;
			}
			
		};
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Tournee)
		{
			Tournee t = (Tournee)o;
			
			this.setListData(fenetres);
		}
		
	}
	
}
