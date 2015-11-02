package modele;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {
	
	//singleton
	private static ReadXML instance = null;
	protected static ReadXML getInstance(){
		if(instance == null) instance = new ReadXML();
		return instance;
	}
	
	//readPlan
	public void readPlan(String url){
		
		try{
			File fXmlFile = new File(url);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Noeud");
		
			for (int i = 0; i < nList.getLength(); i++) {
	
				Node nNode = nList.item(i);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
					Element e = (Element) nNode;
	
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
	    }
	}
	
}
