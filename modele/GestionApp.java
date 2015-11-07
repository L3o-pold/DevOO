package modele;

import org.xml.sax.SAXException;
import xml.XMLOpener;
import xml.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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
        Tournee tournee = new Tournee();
        XMLParser.chargerPlan(plan, xmlOpener.open(false));
    }

}
