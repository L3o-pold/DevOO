package tsp;

import java.util.Collection;
import java.util.Iterator;

public class TSP1 extends TemplateTSP {

	/**
	 * Methode devant etre redefinie par les sous-classes de TemplateTSP
	 * @param sommetCourant
	 * @param nonVus
	 * @return une borne inferieure du cout des chemins de <code>g</code> partant de <code>sommetCourant</code>, visitant 
	 * tous les sommets de <code>nonVus</code> exactement une fois, puis retournant sur le sommet <code>0</code>.
	 */
	@Override
	protected int bound(Integer sommetCourant, Collection<Integer> nonVus) {
		return 0;
	}

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, Collection<Integer> nonVus, Graphe g) {
		return new IteratorSeq(nonVus, sommetCrt, g);
	}

}
