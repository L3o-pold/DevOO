package modele;


import org.xml.sax.SAXException;
import xml.XMLOpener;
import xml.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class GestionApp {

    /*--- Attributes ---*/
    

    /*--- Constructor ---*/

    public GestionApp() {

    }

    /*--- Public methods ---*/

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Plan plan = new Plan();

        XMLOpener xmlOpener = XMLOpener.getInstance();
        File xmlFilePlan = xmlOpener.open(true);
        if(xmlFilePlan != null) {
            XMLParser.chargerPlan(plan, xmlFilePlan);

            File xmlFileLivraisons = xmlOpener.open(true);
            if(xmlFileLivraisons != null) {
                XMLParser.chargerLivraisons(plan, xmlFileLivraisons);

                FenetreLivraison fl = plan.getTournee().getFenetres().get(0);
                System.out.println(fl.getHeureDebut());
                System.out.println(fl.getHeureFin());
                System.out.println(fl.getLivraisons().size());

                for(Livraison l : fl.getLivraisons()) {
                    System.out.println("#" + l.getId() + " " + l.getIntersection().getId());
                }
            }
        }

    }

}
