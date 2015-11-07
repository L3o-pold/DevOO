package modele;

import org.xml.sax.SAXException;
import xml.XMLOpener;
import xml.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class GestionApp {

    /*--- Attributes ---*/

    private Plan plan;
    private Tournee tournee;

    /*--- Constructor ---*/

    public GestionApp() {

    }

    /*--- Public methods ---*/

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        XMLOpener xmlOpener = new XMLOpener();
        Plan plan = new Plan();
        XMLParser.chargerPlan(plan, xmlOpener.open(false));

        List<Intersection> intersections = plan.getIntersections();
        List<Troncon> troncons = plan.getTroncons();

        for(Intersection intersection : intersections) {
            System.out.println("Intersection #" + intersection.getId() + " (" + intersection.getX() + ", " + intersection.getY() + ")");
        }

        for(Troncon troncon : troncons) {
            System.out.println("Troncon: " + troncon.getDepart().getId() + " -" + troncon.getDuree() + "-> " + troncon.getArrivee().getId());
        }



    }

}
