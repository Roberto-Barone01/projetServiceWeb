package upem.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpemSession extends Remote {
	
	public int startSession(String name, String password) throws RemoteException;


}
