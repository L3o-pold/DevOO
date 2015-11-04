package modele;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GestionApp {
    /*--- Public methods ---*/
    public static void main(String [] args)
    {
    	
    	Plan plan = ReadXML.getInstance().chargerPlan("./xml/plan10x10.xml");
    	Pair<Intersection, ArrayList<FenetreLivraison>> pair = ReadXML.getInstance().chargerLivraison("./xml/livraison10x10-1.xml",plan);
    	
    	SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        
        System.out.println("entrepot id:"+pair.getFirst().getId());
        
        int i = 0;
        while(plan.getIntersectionById(i)!=null){
        	i++;
        	System.out.println("intersection id = "+plan.getIntersectionById(i).getId()+" , X= "+plan.getIntersectionById(i).getX()+" , Y="+plan.getIntersectionById(i).getY());
        }
        System.out.println();
        for(int j = 0; j<pair.getSecond().size(); j++){
        	System.out.println("Plage Horaire "+j+" : from "+ft.format(pair.getSecond().get(j).getHeureDebut())+" to "+ft.format(pair.getSecond().get(j).getHeureFin()));
        	for(int k = 0; k<pair.getSecond().get(j).getLivraisons().size(); k++){
        		System.out.println("Livraison "+pair.getSecond().get(j).getLivraisons().get(k).getOrdre()+" : to intersection"+pair.getSecond().get(j).getLivraisons().get(k).getIntersectionLivraison().getId());
        	}
        System.out.println();
        }
        
    	/**
    	String s = "602,100000";
        s.replace(',', '.');
        System.out.println(s);
        **/
    	
     }
    
    }


        
        
    
    

