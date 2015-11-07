package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tsp.GrapheEtapes;
import tsp.TSP;
import tsp.TSP1;

public class Tournee {

	/*--- Attributes ---*/

	private List<Etape> etapes;
	private List<FenetreLivraison> fenetres;
	private double dureeTotale;

	/*--- Constructor ---*/

	public Tournee() {
		this.etapes = new ArrayList<Etape>();
		this.fenetres = new ArrayList<FenetreLivraison>();
        this.dureeTotale = 0.;
	}

	/*--- Accessors ---*/

	public List<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(List<Etape> etapes) {
		this.etapes = etapes;
	}

	public List<FenetreLivraison> getFenetres() {
		return fenetres;
	}

	public void addEtape(Etape e) {
		this.etapes.add(e);
	}

	public void addFenetre(FenetreLivraison fl) {
		this.fenetres.add(fl);
	}

	public void removeFenetre(FenetreLivraison fl) {
		this.fenetres.remove(fl);
	}
	
	public double getDureeTotale() {
		return dureeTotale;
	}

	public void setDureeTotale(double dureeTotale) {
		this.dureeTotale = dureeTotale;
	}

	public Etape getEtape(int idDepart, int idArrivee)
	{
        for(Etape etape : this.etapes) {
            if(etape.getIdDepart() == idDepart && etape.getIdArrivee() == idArrivee) {
                return etape;
            }
        }

        return null;
    }
	
	/**
	 * Permet de compter le nombre de livraisons � faire, afin notamment
	 * d'instancier le graphe
	 * 
	 * @return le nombre de livraisons
	 */
	public int getNbLivraisons() {
		int nbLivraisons = 0;
		for (FenetreLivraison fl : this.fenetres) {
            nbLivraisons += this.fenetres.size();
		}
		return nbLivraisons;
	}

	/**
	 * Permet de calculer la tourn�e
	 * 
	 * @param p
	 *            le plan contenant l'ensemble des intersections et les m�thodes
	 *            de calcul de chemin le plus court
	 */
	public void calculTournee(Plan p) {
	
		// On rajoute 1 car l'entrepot est un sommet suppl�mentaire du graphe
		// (Et pas une livraison)
		GrapheEtapes graphe = new GrapheEtapes(this.getNbLivraisons() + 1);

		// Instanciations
		List<Troncon> plusCourtChemin = new ArrayList<Troncon>();
		Etape etape;

		// On récupère l'entrepot et les livraisons de la première fenêtre de
		// livraison
		Intersection entrepot = p.getEntrepot();

		FenetreLivraison fenetreLivraison1;
		FenetreLivraison fenetreLivraison2;
		Iterator<FenetreLivraison> it = fenetres.iterator();
		fenetreLivraison1 = it.next();
		List<Livraison> livraisons1;
		List<Livraison> livraisons2;
		livraisons1 = fenetreLivraison1.getLivraisons();

		for (Livraison l : livraisons1) {
			// On calcule tous les plus courts chemins entre l'entrepot et les
			// livraison
			plusCourtChemin = p.plusCourtChemin(entrepot, l.getIntersection());
			etape = new Etape(0, l.getOrdre(), plusCourtChemin);
			graphe.ajouterEtape(etape);
			this.addEtape(etape);
			for (Livraison l2 : livraisons1) {
				if (l != l2) {
					// On calcule ici tous les plus courts chemins entre chaque
					// livraison de la fenetre
					plusCourtChemin = p.plusCourtChemin(l.getIntersection(), l2.getIntersection());
					etape = new Etape(l.getOrdre(), l2.getOrdre(), plusCourtChemin);
					graphe.ajouterEtape(etape);
					this.addEtape(etape);
				}
			}
		}

		// On passe ensuite aux autres fenêtres de livraisons
		while (it.hasNext()) {
			fenetreLivraison2 = (FenetreLivraison) it.next();
			livraisons2 = fenetreLivraison2.getLivraisons();
			for (Livraison l : livraisons1) {
				for (Livraison l2 : livraisons2) {
					// Plus court chemin entre les livraisons de la fenetre
					// précédente et celle en cours
					plusCourtChemin = p.plusCourtChemin(l.getIntersection(), l2.getIntersection());
					etape = new Etape(l.getOrdre(), l2.getOrdre(), plusCourtChemin);
					graphe.ajouterEtape(etape);
					this.addEtape(etape);
					for (Livraison l3 : livraisons2) {
						if (l2 != l3) {
							// Plus court chemin entre les livraisons de la même fenêtre
							plusCourtChemin = p.plusCourtChemin(l2.getIntersection(),
									l3.getIntersection());
							etape = new Etape(l2.getOrdre(), l3.getOrdre(), plusCourtChemin);
							graphe.ajouterEtape(etape);
							this.addEtape(etape);
						}
					}
				}
			}

			fenetreLivraison1 = fenetreLivraison2;
			livraisons1 = livraisons2;
		}

		// Retour à l'entrepot
		for (Livraison l : livraisons1) {
			plusCourtChemin = p.plusCourtChemin(l.getIntersection(), entrepot);
			etape = new Etape(l.getOrdre(), 0, plusCourtChemin);
			graphe.ajouterEtape(etape);
			this.addEtape(etape);
		}
		
		// Appel a TSP

		List<Etape> etapesFinales = new ArrayList<Etape>();
		TSP tsp = new TSP1();
		tsp.chercheSolution(60000, graphe);
		this.setDureeTotale(tsp.getCoutSolution());
		for (int i = 0; i < graphe.getNbSommets()-1; i++)
		{
			etapesFinales.add(this.getEtape(tsp.getSolution(i), tsp.getSolution(i+1)));
		}
		
		etapesFinales.add(this.getEtape(tsp.getSolution(graphe.getNbSommets()-1), 0));
		
		this.setEtapes(etapesFinales);
		
		//BOUCLE POUR AFFICHER LE CHEMIN COMPLET DE LA TOURNEE
		for( int i = 0; i < this.etapes.size(); i++)
		{
			Etape e = this.etapes.get(i);
			System.out.println("Etape " + i + " : ");
			List<Troncon> troncons = e.getTroncons();
			for( int j = 0; j < troncons.size() ; j++ )
			{
				Troncon t = troncons.get(j);
				System.out.println( t.getDepart().getId() + " -> " + t.getArrivee().getId() );
			}
		}
		
	}
}
