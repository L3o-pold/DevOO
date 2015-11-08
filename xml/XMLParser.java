package xml;

import modele.*;
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
import java.util.Date;

public class XMLParser {

    private XMLParser() {

    }

    public static void chargerPlan(Plan plan, File xmlFile) throws IOException, SAXException, ParserConfigurationException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList intersections = doc.getElementsByTagName("Noeud");

        if (intersections.getLength() < 1) {
            throw new IOException("Fichier du plan invalide");
        }

        for(int i = 0; i < intersections.getLength(); i++) {
            Node intersection = intersections.item(i);
            if (intersection.getNodeType() == Node.ELEMENT_NODE) {
                Element eIntersection = (Element) intersection;

                int id = Integer.parseInt(eIntersection.getAttribute("id"));
                int x = Integer.parseInt(eIntersection.getAttribute("x"));
                int y = Integer.parseInt(eIntersection.getAttribute("y"));

                if (plan.getIntersectionById(id) == null) {
                    plan.addIntersection(new Intersection(id, x, y));
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

                        String strVitesse = eTroncon.getAttribute("vitesse");
                        String strLongueur = eTroncon.getAttribute("longueur");

                        strVitesse = strVitesse.replace(",", ".");
                        strLongueur = strLongueur.replace(",", ".");

                        double duree = Double.parseDouble(strLongueur) / Double.parseDouble(strVitesse);
                        int idIntersectionDepart = Integer.parseInt(eIntersection.getAttribute("id"));
                        int idIntersectionArrive = Integer.parseInt(eTroncon.getAttribute("idNoeudDestination"));

                        Intersection depart = plan.getIntersectionById(idIntersectionDepart);
                        Intersection arrive = plan.getIntersectionById(idIntersectionArrive);

                        if (depart != null && arrive != null) {
                            plan.addTroncon(new Troncon(duree, depart, arrive));
                        }
                    }
                }
            }
        }

    }

    public static void chargerLivraisons(Plan plan, File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        Element eEntrepot = (Element) (doc.getElementsByTagName("Entrepot").item(0));

        if (eEntrepot.hasAttribute("adresse") == false) {
            throw new IOException("Fichier de livraison invalide");
        }

        int idEntrepot = Integer.parseInt(eEntrepot.getAttribute("adresse"));

        Intersection entrepot = plan.getIntersectionById(idEntrepot);

        if(entrepot == null) {
            return;
        }

        plan.setTournee(new Tournee(entrepot));

        NodeList plages = doc.getElementsByTagName("Plage");

        for(int i = 0; i < plages.getLength(); i++) {
            Node plage = plages.item(i);
            if (plage.getNodeType() == Node.ELEMENT_NODE) {
                Element ePlage = (Element) plage;
                Date heureDebut = parseDate(ePlage.getAttribute("heureDebut"));
                Date heureFin = parseDate(ePlage.getAttribute("heureFin"));

                FenetreLivraison fl = new FenetreLivraison(heureDebut, heureFin);

                NodeList livraisons = ePlage.getElementsByTagName("Livraison");
                for(int j = 0; j < livraisons.getLength(); j++) {
                    Node livraison = livraisons.item(j);
                    if(livraison.getNodeType() == Node.ELEMENT_NODE) {
                        Element eLivraison = (Element) livraison;

                        int idLivraison = Integer.parseInt(eLivraison.getAttribute("id"));
                        int idIntersection = Integer.parseInt(eLivraison.getAttribute("adresse"));

                        Intersection intersectionLivraison = plan.getIntersectionById(idIntersection);

                        if(intersectionLivraison != null) {
                            fl.addLivraison(new Livraison(idLivraison, intersectionLivraison));
                        }
                    }
                }

                plan.getTournee().addFenetre(fl);
            }
        }

    }

    private static Date parseDate(String strDate) {
        String[] tmp = strDate.split(":");
        int hours = Integer.parseInt(tmp[0]);
        int minutes = Integer.parseInt(tmp[1]);
        int seconds = Integer.parseInt(tmp[2]);

        Date date = new Date();
        date.setHours(hours);
        date.setMinutes(minutes);
        date.setSeconds(seconds);

        return date;
    }
}
