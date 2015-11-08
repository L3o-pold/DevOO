package modele;

import controleur.Controleur;
import org.xml.sax.SAXException;
import xml.XMLOpener;
import xml.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

public class Livrak extends Observable {

    /*--- Attributes ---*/

    Plan plan;
    Tournee tournee;

    /*--- Constructor ---*/

    public Livrak() {
        this.plan = new Plan();
    }

    /*--- Accessors ---*/

    public Plan getPlan() {
        return this.plan;
    }

    public Tournee getTournee() {
        return this.tournee;
    }

    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }

    /*--- Public methods ---*/

    public void chargerPlan() {
        XMLOpener xmlOpener = XMLOpener.getInstance();

        try {
            File xmlFile = xmlOpener.open(false);
            if(xmlFile != null){
                XMLParser.chargerPlan(this, xmlFile);
                setChanged();
                notifyObservers(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chargerLivraisons() {
        XMLOpener xmlOpener = XMLOpener.getInstance();

        try {
            File xmlFile = xmlOpener.open(false);
            if(xmlFile != null){
                XMLParser.chargerLivraisons(this, xmlFile);
                setChanged();
                notifyObservers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Livrak app = new Livrak();
        Controleur c = new Controleur(app);

        c.chargerPlan();
        c.chargerLivraisons();

        FenetreLivraison fl = app.getTournee().getFenetres().get(0);
        System.out.println(fl.getHeureDebut());
        System.out.println(fl.getHeureFin());
        System.out.println(fl.getLivraisons().size());

        for(Livraison l : fl.getLivraisons()) {
            System.out.println("#" + l.getId() + " " + l.getIntersection().getId());
        }
    }

}
