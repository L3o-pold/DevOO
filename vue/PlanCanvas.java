package vue;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import modele.Intersection;
import modele.Plan;
import modele.Tournee;
import modele.Troncon;

public class PlanCanvas extends Canvas {

	public static final int INTERSECTION_RADIUS = 15;
	public static final int MARGIN_SECURITY = 10;
	private float widthRatio;
	private float heightRatio;
	private Plan plan;

	public PlanCanvas( Plan plan )
	{
		super();
		this.plan = plan;
		this.plan.setEntrepot(this.plan.getIntersectionById(0));
	}
	
	public void paint(Graphics g) {
		
		this.heightRatio = ((float)this.getHeight()-10) / (float)plan.getHauteur();
		this.widthRatio = (((float)this.getWidth())-10) / (float)plan.getLargeur();
		g.setColor(Color.black);

		for( Intersection i : this.plan.getIntersections())
		{	
			this.dessinerIntersection(g, i);
		}
		
		Intersection entrepot = this.plan.getEntrepot();
		g.setColor(Color.PINK);
		
		this.dessinerIntersection(g, entrepot);
		
		g.setColor(Color.black);
		
		for(Troncon t : this.plan.getTroncons())
		{
			dessinerTroncon(g, t);
		}
		
		
		
		
	}

	public void dessinerIntersection(Graphics g, Intersection i) {
		float x = i.getX()*this.widthRatio;
		float y = i.getY()*this.heightRatio;
		//g.drawOval(x, y, this.INTERSECTION_RADIUS, this.INTERSECTION_RADIUS);
		g.fillOval(Math.round(x) - (PlanCanvas.INTERSECTION_RADIUS/2), (Math.round(y) - PlanCanvas.INTERSECTION_RADIUS/2), PlanCanvas.INTERSECTION_RADIUS, PlanCanvas.INTERSECTION_RADIUS);
	}

	public void dessinerTroncon(Graphics g, Troncon t) {
		float x1 = t.getDepart().getX()*this.widthRatio;
		float y1 = t.getDepart().getY()*this.heightRatio;
		float x2 = t.getArrivee().getX()*this.widthRatio;
		float y2 = t.getArrivee().getY()*this.heightRatio;
		g.drawLine(Math.round(x1), Math.round(y1), Math.round(x2), Math.round(y2));
	}
	
	
	public void paintPlan(Plan plan) {

	}
	
	

}
