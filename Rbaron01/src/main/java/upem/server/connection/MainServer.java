package upem.server.connection;

import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class MainServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
	UpemConnection conn = RMIUPEMServiceManager.startConnection();

	}

}
