package upem.server.connection;

import java.rmi.RemoteException;

public class RMIUPEMServiceManager {
	
	/* Il crée un connection RMI sur la porte 4444 
	 * et ajoute dans le registry un objet UpemService
	 * avec identifie uperService
	 * @return	Un objet qui rappresente une connection en utilisant le protocol RMI 
	 */
	
	public static UpemConnection startConnection() throws RemoteException {
		int port = 4444;
		String identifier = "upemService";
		
		return new UpemConnection(port,identifier);
		
	}

}
