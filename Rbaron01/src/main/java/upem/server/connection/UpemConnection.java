package upem.server.connection;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class UpemConnection {
	
	private final int port;
	private final String prefix;
	private final String identifier;
	
	private final UpemService upemService;
	
	
	UpemConnection(int port, String identifier) throws RemoteException{
		this.identifier = identifier;
		this.port = port;
		this.prefix = "rmi://localhost:";
		this.upemService = new UpemService();
	}
	
	private void init() throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(port);
		Naming.rebind(prefix+""+port+"/"+identifier, upemService);
	}

}
