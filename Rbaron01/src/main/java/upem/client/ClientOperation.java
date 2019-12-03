package upem.client;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import upem.shared.interfaces.UpemServiceRequestable;
import upem.shared.interfaces.UpemServiceRequestable.*;


public class ClientOperation {
	
	public static UpemServiceRequestable req = null;

	public static Map<String, String> user = null;
	
	public static Map<String,String> user() {
		if(user == null) {
			
			try {
				user = new HashMap<String,String>();
				File inputFile = new File("/home/2inlp2/rbaron01/git/projetRMI/Rbaron01/src/main/resources/user.xml");
		        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		        Document doc = dBuilder.parse(inputFile);
		        
		        
		        String text = doc.getFirstChild().getTextContent();
		        String username = text.split("\\s+")[1];
		        String password = text.split("\\s+")[2];
	        
		        user.put("username", username);
		        user.put("password", password);
		        
		        
			}catch (ParserConfigurationException e) {
				user.put("ParserConfigurationException","ParserConfigurationException");
				e.printStackTrace();
			} catch (SAXException e) {
				user.put("SAXException","SAXException");
				e.printStackTrace();
			} catch (IOException e) {
				user.put("IOException","IOException");
				e.printStackTrace();
			}
			
		}
		
		return user;
		
	}
	
	
	public static UpemServiceRequestable getInstance() throws MalformedURLException, RemoteException, NotBoundException {
		user();
		if(req == null) {
			req = (UpemServiceRequestable) Naming.lookup("rmi://localhost:4499/upemService");
		}
		return ClientOperation.req;
	}
	
	
	public static UpemResponse books() throws MalformedURLException, RemoteException, NotBoundException, SQLException{
		UpemServiceRequestable req = getInstance();
		return req.getAllBooks();
	}
	
	public static UpemResponse meta() throws MalformedURLException, RemoteException, NotBoundException, SQLException{
		UpemServiceRequestable req = getInstance();
		return req.getAllMeta();
	}

}
