package upem.server.connection;

import java.rmi.RemoteException;

public class RMIUPEMServiceManager {
	
	
	public static UpemConnection startConnection() throws RemoteException {
		int port = 4444;
		String identifier = "upemService";
		
		return new UpemConnection(port,identifier);
		
	}

}
