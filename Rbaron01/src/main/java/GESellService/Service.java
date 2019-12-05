package GESellService;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import upem.shared.interfaces.UpemServiceRequestable;

public class Service implements Serializable{

	private UpemServiceRequestable connectToServer() {

		try {
			UpemServiceRequestable req = (UpemServiceRequestable) Naming.lookup("rmi://localhost:4499/upemService");
			return req;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean testConnection() {
		return connectToServer() != null;
	}
	
	public String showBook() {
		
		UpemServiceRequestable req = connectToServer();
		try {
			return req.book_client();
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ERROR";
	}
	
	public String addBasket(String user, String password, int id_book) {
		UpemServiceRequestable req = connectToServer();
		try {
			return req.add_to_panier(user, password, id_book);
		} catch (RemoteException e) {
			e.printStackTrace();
			return "ERROR REMOTE";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR SQL";

		}
		
	}
	
	public String buy(String user, String password, double money) {
		UpemServiceRequestable req = connectToServer();
		try {
			return req.buy(user, password, money);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "REMOTE ERROR";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "SQL ERROR";
		}
		
	}
	
}
