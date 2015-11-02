package modele;

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

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
					
					NodeList tList = doc.getElementsByTagName("LeTronconSortant");
					
					for (int j = 0; j < tList.getLength(); j++){
						Node tNode = tList.item(i);
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
							Element t = (Element) tNode;
							
							double longueur = Integer.parseInt(t.getAttribute("longueur"));
							double vitesse = Integer.parseInt(t.getAttribute("vitesse"));
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
	public Entry<Intersection, ArrayList<FenetreLivraison>> chargerLivraison(String url){
		Entry<Intersection, ArrayList<FenetreLivraison>> entry = new Entry<Intersection, ArrayList<FenetreLivraison>>(null,null);
		return list;
	}
}
