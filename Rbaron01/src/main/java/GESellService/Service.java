package GESellService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import upem.shared.interfaces.UpemServiceRequestable;

public class Service {

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
	
}
