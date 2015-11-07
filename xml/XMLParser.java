package xml;

import modele.Plan;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLParser {

    private XMLParser() {

    }

    public static void chargerPlan(Plan plan, File xmlFile) throws IOException, SAXException, ParserConfigurationException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();
        NodeList intersections = doc.getElementsByTagName("Noeud");

        for(int i = 0; i < intersections.getLength(); i++) {
            Node intersection = intersections.item(i);
            if (intersection.getNodeType() == Node.ELEMENT_NODE) {
                Element eIntersection = (Element) intersection;

                int id = Integer.parseInt(eIntersection.getAttribute("id"));
                int x = Integer.parseInt(eIntersection.getAttribute("x"));
                int y = Integer.parseInt(eIntersection.getAttribute("y"));

                if (plan.getIntersectionById(id) == null) {
                    plan.addIntersection(id, x, y);
                }

            }
        }

        for(int i = 0; i < intersections.getLength(); i++) {
            Node intersection = intersections.item(i);
            if (intersection.getNodeType() == Node.ELEMENT_NODE) {
                Element eIntersection = (Element) intersection;
                NodeList troncons = eIntersection.getElementsByTagName("LeTronconSortant");

                for (int j = 0; j < troncons.getLength(); j++) {
                    Node troncon = troncons.item(j);
                    if (troncon.getNodeType() == Node.ELEMENT_NODE) {
                        Element eTroncon = (Element) troncon;

                        String str_vitesse = eTroncon.getAttribute("vitesse");
                        String str_longueur = eTroncon.getAttribute("longueur");

                        str_vitesse = str_vitesse.replace(",", ".");
                        str_longueur = str_longueur.replace(",", ".");

                        double duree = Double.parseDouble(str_longueur) / Double.parseDouble(str_vitesse);
                        int idIntersectionDepart = Integer.parseInt(eIntersection.getAttribute("id"));
                        int idIntersectionArrive = Integer.parseInt(eTroncon.getAttribute("idNoeudDestination"));

                        if (plan.getIntersectionById(idIntersectionArrive) != null) {
                            plan.addTroncon(duree, idIntersectionDepart, idIntersectionArrive);
                            // add the troncon to the intersection ?
                        }
                    }
                }
            }
        }

    }
}
