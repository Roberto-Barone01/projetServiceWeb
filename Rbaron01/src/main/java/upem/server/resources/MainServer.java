package upem.server.resources;

import java.rmi.RemoteException;

import upem.server.connection.RMIUPEMServiceManager;
import upem.server.connection.UpemConnection;

public class MainServer {

	public static void main(String[] args) throws RemoteException {
		
		UpemConnection conn = RMIUPEMServiceManager.startConnection();

	}

}
