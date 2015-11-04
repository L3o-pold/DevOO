package modele;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {
	
	//singleton
	private static ReadXML instance = null;
	protected static ReadXML getInstance(){
		if(instance == null) instance = new ReadXML();
		return instance;
	}
	
	//readPlan
	public Plan chargerPlan(String url){
		Plan plan = new Plan();
		try{
			File fXmlFile = new File(url);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Noeud");
		
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) nNode;
					
					int id = Integer.parseInt(e.getAttribute("id"));
					int x = Integer.parseInt(e.getAttribute("x"));
					int y = Integer.parseInt(e.getAttribute("y"));
					
					Intersection is = plan.getIntersectionById(id);
					
					if(is==null){
						is = new Intersection(id,x,y);
					}else{
						is.setX(x);
						is.setY(y);
					}
					
					plan.addIntersection(is);
					
					NodeList tList = e.getElementsByTagName("LeTronconSortant");
					
					for (int j = 0; j < tList.getLength(); j++){
						Node tNode = tList.item(i);
						if (tNode.getNodeType() == Node.ELEMENT_NODE) {
							Element t = (Element) tNode;
							
							String[] sLongueurs = t.getAttribute("longueur").split(",");
							String sLongueur = sLongueurs[0]+"."+sLongueurs[1];
							double longueur = Double.parseDouble(sLongueur);
							String[] sVitesses = t.getAttribute("vitesse").split(",");
							String sVitesse = sVitesses[0]+"."+sVitesses[1];
							double vitesse = Double.parseDouble(sVitesse);
							double duree = longueur/vitesse;
							int iddest = Integer.parseInt(t.getAttribute("idNoeudDestination"));
							
							Intersection isdest = plan.getIntersectionById(iddest);
							
							if(isdest==null){
								isdest = new Intersection(iddest,Integer.MIN_VALUE,Integer.MIN_VALUE);
							}
							Troncon tc = new Troncon(duree,is,isdest);
							is.ajouterTroncon(tc);
						}
					}
				}
			}
			//validate
			//TODO

		}catch(Exception e) {
			e.printStackTrace();
	    }
		return plan;
	}
	
	public Pair<Intersection, ArrayList<FenetreLivraison>> chargerLivraison(String url,Plan plan){
		Pair<Intersection, ArrayList<FenetreLivraison>> pair = new Pair<Intersection, ArrayList<FenetreLivraison>>(null,null);
		
		try{
			File fXmlFile = new File(url);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			doc.getDocumentElement().normalize();
			
			//chargerEntrepot
			Element eEntrepot = doc.getElementById("Entrepot");
			int identrepot = Integer.parseInt(eEntrepot.getAttribute("adresse"));
			Intersection entrepot = plan.getIntersectionById(identrepot);
			pair.setFirst(entrepot);
			
			//chargerPlageHoraire
			ArrayList<FenetreLivraison> alLiv = new ArrayList<FenetreLivraison>();
			NodeList fList = doc.getElementsByTagName("Plage");
			for (int i = 0; i < fList.getLength(); i++){
				Node fNode = fList.item(i);
				if (fNode.getNodeType() == Node.ELEMENT_NODE) {
					Element f = (Element) fNode;
					
					String sHeureDebut = f.getAttribute("heureDebut");
					String sHeureFin = f.getAttribute("heureFin");
					
					Date dHeureDebut = new Date();
					Date dHeureFin = new Date();
					SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
					
					dHeureDebut = ft.parse(sHeureDebut) ;
					dHeureFin = ft.parse(sHeureFin);
					
					FenetreLivraison fl = new FenetreLivraison(dHeureDebut,dHeureFin);
					alLiv.add(fl);
					
					//chargerLivraison
					NodeList lList = f.getElementsByTagName("Livraison");
					for (int j = 0; j < fList.getLength(); j++){
						Node lNode = lList.item(i);
						if (lNode.getNodeType() == Node.ELEMENT_NODE) {
							Element l = (Element) fNode;
							
							int idIsLiv = Integer.parseInt(l.getAttribute("id"));
							Intersection isLiv = plan.getIntersectionById(idIsLiv);
							
							Livraison livraison = new Livraison(isLiv);
							livraison.setOrdre(j);
							
							fl.addLivraison(livraison);
						}
					}
				}
			}
			pair.setSecond(alLiv);
		}catch(Exception e) {
			e.printStackTrace();
	    }
		return pair;
	}
}
