package vue;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import modele.Etape;
import modele.Intersection;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

public class VueGraphique extends Canvas implements Observer {

	public static final int INTERSECTION_RADIUS = 8;
	public static final int MARGIN_SECURITY = 10;
	private float widthRatio;
	private float heightRatio;
	private Plan plan;

	public VueGraphique( Plan plan )
	{
		super();
		this.plan = plan;
		//this.plan.setEntrepot(this.plan.getIntersectionById(0));
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.heightRatio = ((float)this.getHeight()-10) / (float)plan.getHauteur();
		this.widthRatio = (((float)this.getWidth())-10) / (float)plan.getLargeur();
		g.setColor(Color.black);

		for( Intersection i : this.plan.getIntersections())
		{	
			this.dessinerIntersection(g2, i);
		}
		
		
		g.setColor(Color.black);
		
		for(Troncon t : this.plan.getTroncons())
		{
			dessinerTroncon(g2, t);
		}
		
		if (this.plan.getTournee() != null)
		{
			dessinerEntrepot(g2, this.plan.getTournee().getEntrepot());
			dessinerTronconsTournee(g2);
		}
		
	}

	public void dessinerIntersection(Graphics2D g, Intersection i) {
		double x = i.getX()*this.widthRatio;
		double y = i.getY()*this.heightRatio;
		g.fill(new Ellipse2D.Double(x - VueGraphique.INTERSECTION_RADIUS, y - VueGraphique.INTERSECTION_RADIUS, VueGraphique.INTERSECTION_RADIUS*2, VueGraphique.INTERSECTION_RADIUS*2));
	}

	public void dessinerTroncon(Graphics2D g, Troncon t) {
		double x1 = t.getDepart().getX()*this.widthRatio;
		double y1 = t.getDepart().getY()*this.heightRatio;
		double x2 = t.getArrivee().getX()*this.widthRatio;
		double y2 = t.getArrivee().getY()*this.heightRatio;
		g.setStroke(new BasicStroke(1.2f));
		g.draw(new Line2D.Double(x1, y1, x2, y2));
	}
	
	public void dessinerTronconsTournee(Graphics2D g) {
		
		int nbTroncons=0;
		for(Etape e : this.plan.getTournee().getEtapes() )
		{
			nbTroncons += e.getTroncons().size();
		}
		
		float red = 255;
		float green = 255;
		float blue = 0;
		float step = 255 / nbTroncons;
		
		for(Etape e : this.plan.getTournee().getEtapes() )
		{
			//g.setColor(Color.RED);
			dessinerIntersection(g,e.getTroncons().get(0).getDepart());
			for(Troncon t : e.getTroncons() )
			{
				double x1 = t.getDepart().getX()*this.widthRatio;
				double y1 = t.getDepart().getY()*this.heightRatio;
				double x2 = t.getArrivee().getX()*this.widthRatio;
				double y2 = t.getArrivee().getY()*this.heightRatio;
				
				g.setStroke(new BasicStroke((2f),
								BasicStroke.CAP_BUTT,
								BasicStroke.JOIN_ROUND));
				g.setColor(new Color(red/255,green/255,blue/255));
				green -= step;
				//Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
				Line2D.Double line = calculerLigne(x1, y1, x2, y2);
				g.draw(line);
				drawArrowHead(g, line);
			}
		}
		
	}
	
	public void dessinerEntrepot(Graphics2D g, Intersection i) {
		g.setColor(Color.RED);
		double x = i.getX()*this.widthRatio;
		double y = i.getY()*this.heightRatio;
		g.fill(new Ellipse2D.Double(x - VueGraphique.INTERSECTION_RADIUS, y - VueGraphique.INTERSECTION_RADIUS, VueGraphique.INTERSECTION_RADIUS*2, VueGraphique.INTERSECTION_RADIUS*2));
	}
	
	public void drawArrowHead(Graphics2D g2d, Line2D.Double line) {
		AffineTransform tx = new AffineTransform();
		Polygon arrowHead = new Polygon();  
		arrowHead.addPoint( 0,0);
		arrowHead.addPoint( -5, -5);
		arrowHead.addPoint( 5,-5);
	    tx.setToIdentity();
	    double angle = Math.atan2(line.y2-line.y1, line.x2-line.x1);
	    tx.translate(line.x2, line.y2);
	    tx.rotate((angle-Math.PI/2d));  
	    Graphics2D g = (Graphics2D) g2d.create();
	    g.setTransform(tx);   
	    g.fill(arrowHead);
	    g.dispose();
	}
	
	public Line2D.Double calculerLigne(Double x1, Double y1,Double x2,Double y2)
	{
		Double x;
		Double y;
		x = x2 - x1;
		y = y2 - y1;
		Double longueur = Math.sqrt(x*x + y*y);
		x = x * (1/longueur);
		y = y * (1/longueur);
		x = x *  (longueur - VueGraphique.INTERSECTION_RADIUS);
		y = y * (longueur - VueGraphique.INTERSECTION_RADIUS);
		Line2D.Double line = new Line2D.Double(x2 - x, y2 - y, x1 + x, y1 + y);
		return line;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if( o instanceof Plan)
		{
			this.plan = (Plan)o;
			repaint();
		}
	}
	
	

}
