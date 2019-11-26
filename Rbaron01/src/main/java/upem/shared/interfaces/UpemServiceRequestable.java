package upem.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UpemServiceRequestable extends Remote{

	public int myId() throws RemoteException;
	public boolean isConnected() throws RemoteException;
	
	ArrayList<String> getAllResources() throws RemoteException;
	ArrayList<String> getResources(boolean meta) throws RemoteException;
	ArrayList<String> getAllBooks() throws RemoteException;
	
	String getDatesfResource(boolean meta, int id) throws RemoteException;
	String getDatesfBook(int id) throws RemoteException;
	String getDatesOfMetaProduct(int id) throws RemoteException;
	
	boolean tryToGetResource(boolean meta, String user, int id, boolean addMe) throws RemoteException;
	boolean tryToGetBook(String  user, int id, boolean addMe) throws RemoteException;
	boolean tryToGetMetaProduct(String user, int id, boolean addMe) throws RemoteException;
	
	
	void removeMeQueueResource(boolean meta, int id) throws RemoteException;
	void removeMeQueueBook(int id) throws RemoteException;
	void removeMeQueueMetaProduct(int id) throws RemoteException;
	
	ArrayList<String> showQueueResources(boolean meta, int id) throws RemoteException;
	ArrayList<String> showQueueBook(int id) throws RemoteException;
	ArrayList<String> showQueueMetaProduct(int id) throws RemoteException;
	
        
        void removeResource(int id, boolean meta) throws RemoteException;
        void removeBook(int id) throws RemoteException;
        void removeMetaResource(int id) throws RemoteException;
}
