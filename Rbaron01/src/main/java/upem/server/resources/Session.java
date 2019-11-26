package upem.server.resources;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import upem.shared.interfaces.UpemSession;

public class Session extends UnicastRemoteObject implements UpemSession {

	Session() throws RemoteException{
		super();
	}
	
	@Override
	public int startSession(String name, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}


}
