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

import upem.shared.interfaces.BookProperty;
import upem.shared.interfaces.MetaProperty;
import upem.shared.interfaces.UpemServiceRequestable;
import upem.shared.interfaces.UpemServiceRequestable.*;


public class ClientOperation {
	
	public static UpemServiceRequestable req = null;

	public static Map<String, String> user = null;
	
	public static Map<String,String> user() {
		if(user == null) {
			
			try {
				user = new HashMap<String,String>();
				File inputFile = new File("C:\\Users\\rober\\git\\projetServiceWeb\\Rbaron01\\src\\main\\resources\\main\\java\\upem\\client\\user.xml");
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
	
	public static UnaryUpemResponse tryToGetBook(int id_book) throws MalformedURLException, RemoteException, NotBoundException, SQLException{
		UpemServiceRequestable req = getInstance();
		return req.tryToGetBook(user.get("username"), user.get("password"), id_book, true);
	}
	
	public static UnaryUpemResponse tryToGetMeta(int id_meta) throws MalformedURLException, RemoteException, NotBoundException, SQLException{
		UpemServiceRequestable req = getInstance();
		return req.tryToGetMeta(user.get("username"), user.get("password"), id_meta, true);
	}
	
	public static void addBook(String title, String publisher, String edition, String isbn, String pages, String year, String comment, String state) throws RemoteException, SQLException, MalformedURLException, NotBoundException {
		UpemServiceRequestable req = getInstance();
		BookProperty book = req.initialiseBook();
		book.title(title).publisher(publisher).edition(edition).isbn(isbn).
		pages(Integer.parseInt(pages)).year(year).comment(comment).state(state);
		req.addBook(user.get("username"), user.get("password"), book);
	}
	
	public static void addMeta(String name, String type, String comment, String state) throws RemoteException, SQLException, MalformedURLException, NotBoundException {
		UpemServiceRequestable req = getInstance();
		MetaProperty meta = req.initialiseMeta();
		meta.meta_name(name).meta_type(type).comment(comment).state(state);
		req.addMetaResource(user.get("username"), user.get("password"), meta);
	}
	
	public static UpemResponse userResources(boolean meta) throws RemoteException, SQLException, MalformedURLException, NotBoundException {
		UpemServiceRequestable req = getInstance();
		if(!meta)
			return req.showMyBooks(user.get("username"), user.get("password"));
		else 
			return req.showMyMetas(user.get("username"), user.get("password"));
	}
	
	public static void deleteRis(boolean meta, int id) throws MalformedURLException, RemoteException, NotBoundException, SQLException {
		UpemServiceRequestable req = getInstance();
		if(meta)
			req.removeMeta(user.get("username"), user.get("password"), id);
		else
			req.removeBook(user.get("username"), user.get("password"), id);
	}

}
