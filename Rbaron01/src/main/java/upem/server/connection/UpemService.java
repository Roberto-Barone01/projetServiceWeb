package upem.server.connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import upem.shared.interfaces.UpemServiceRequestable;

public class UpemService extends UnicastRemoteObject implements UpemServiceRequestable{

	UpemService() throws RemoteException{
		super();
	}
	
	@Override
	public boolean startSession(String user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> getAllResources() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getResources(boolean meta) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllBooks() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDatesfResource(boolean meta, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDatesfBook(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDateslogyOfMetaProduct(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryToGetResource(boolean meta, String user, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryToGetBook(String user, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryToGetMetaProduct(String user, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addMeToQueueResource(String user, boolean meta, int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMeToQueueBook(String user, int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMeToQueueMetaProduct(String user, int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMeQueueResource(String user, boolean meta, int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMeQueueBook(String user, int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMeQueueMetaProduct(String user, int id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> showQueueResources(boolean meta, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> showQueueBook(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> showQueueMetaProduct(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
