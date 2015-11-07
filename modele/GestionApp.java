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

	public void setTournee(Tournee tournee) {
		this.tournee = tournee;
	}

    
    /*--- Public methods ---*/
    public static void main(String [] args)
    {
        GestionApp ga = new GestionApp();
        
        //Intersections

        Intersection i0 = new Intersection(0,63,100);
        Intersection i1 = new Intersection(1,88,171);
        Intersection i2 = new Intersection(2,103,248);
        Intersection i3 = new Intersection(3,65,310);
        Intersection i4 = new Intersection(4,77,350);
        Intersection i5 = new Intersection(5,83,403);
        Intersection i6 = new Intersection(6,87,843);
        Intersection i7 = new Intersection(7,45,579);
        Intersection i8 = new Intersection(8,43,619);
        Intersection i9 = new Intersection(9,59,699);
        Intersection i10 = new Intersection(10,154,95);


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
