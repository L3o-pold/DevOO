package vue;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;

import modele.FenetreLivraison;

public class VueTextuelle extends JList implements Observer{

	private List<FenetreLivraison> fenetres;
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
