package vue;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
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

	public static final int INTERSECTION_RADIUS = 15;
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
			for(Etape e : this.plan.getTournee().getEtapes())
			{
				for( Troncon t : e.getTroncons())
				{
					dessinerTronconTournee(g2,t);
				}
			}
		}
		
	}

	public void dessinerIntersection(Graphics2D g, Intersection i) {
		double x = i.getX()*this.widthRatio;
		double y = i.getY()*this.heightRatio;
		g.fill(new Ellipse2D.Double(x - VueGraphique.INTERSECTION_RADIUS/2, y - VueGraphique.INTERSECTION_RADIUS/2, VueGraphique.INTERSECTION_RADIUS, VueGraphique.INTERSECTION_RADIUS));
	}

	public void dessinerTroncon(Graphics2D g, Troncon t) {
		double x1 = t.getDepart().getX()*this.widthRatio;
		double y1 = t.getDepart().getY()*this.heightRatio;
		double x2 = t.getArrivee().getX()*this.widthRatio;
		double y2 = t.getArrivee().getY()*this.heightRatio;
		g.setStroke(new BasicStroke(1.2f));
		g.draw(new Line2D.Double(x1, y1, x2, y2));
	}
	
	public void dessinerTronconTournee(Graphics2D g, Troncon t) {
		double x1 = t.getDepart().getX()*this.widthRatio;
		double y1 = t.getDepart().getY()*this.heightRatio;
		double x2 = t.getArrivee().getX()*this.widthRatio;
		double y2 = t.getArrivee().getY()*this.heightRatio;
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke((2f),
						BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_ROUND));
		System.out.println("aa");
		g.draw(new Line2D.Double(x1, y1, x2, y2));
	}
	
	public void dessinerEntrepot(Graphics2D g, Intersection i) {
		g.setColor(Color.RED);
		double x = i.getX()*this.widthRatio;
		double y = i.getY()*this.heightRatio;
		g.fill(new Ellipse2D.Double(x - VueGraphique.INTERSECTION_RADIUS/2, y - VueGraphique.INTERSECTION_RADIUS/2, VueGraphique.INTERSECTION_RADIUS, VueGraphique.INTERSECTION_RADIUS));
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
