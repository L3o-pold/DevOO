package tsp;

import modele.Etape;

public class GrapheEtapes implements Graphe{

	private static final int COUT_MAX = 40;
	private static final int COUT_MIN = 10;
	int nbSommets;
	int[][] cout;
	
	public GrapheEtapes(int nbSommets){
		this.nbSommets = nbSommets;
		cout = new int[nbSommets][nbSommets];
	}
	
	public void ajouterEtape(Etape e) {
		cout[e.getIdDepart()][e.getIdArrivee()] = (int) e.getDuree();
	}
	
	@Override
	public int getNbSommets() {
		
		return this.nbSommets;
	}

	@Override
	public int getCout(int i, int j) {
		return this.cout[i][j];
	}

	@Override
	public boolean estArc(int i, int j) {
		if( this.cout[i][j] > 0)
		{
			return true;
		}
		return false;
	}

}
