package upem.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;


public interface UpemServiceRequestable extends Remote{
    
    
        interface UpemResponse extends Remote{
            public int code() throws RemoteException;
            public Map<String,String> result() throws RemoteException;
        }
    

	boolean isConnected() throws RemoteException;
	
	UpemResponse getAllResources() throws RemoteException;
	UpemResponse getResources(boolean meta) throws RemoteException;
	UpemResponse getAllBooks() throws RemoteException;
	
	UpemResponse getDatesfResource(boolean meta, int id) throws RemoteException;
	UpemResponse getDatesfBook(int id) throws RemoteException;
	UpemResponse getDatesOfMetaProduct(int id) throws RemoteException;
	
	boolean tryToGetResource(String user, String password, boolean meta, int id, boolean addMe) throws RemoteException;
	boolean tryToGetBook(String user, String password, int id, boolean addMe) throws RemoteException;
	boolean tryToGetMetaProduct(String user,String password, int id, boolean addMe) throws RemoteException;
	
	
	boolean removeMeQueueResource(String User, String password, boolean meta, int id) throws RemoteException;
	boolean removeMeQueueBook(String User, String password, int id) throws RemoteException;
	boolean removeMeQueueMetaProduct(String User, String password, int id) throws RemoteException;
	
	UpemResponse showQueueResources(boolean meta, int id) throws RemoteException;
	UpemResponse showQueueBook(int id) throws RemoteException;
	UpemResponse showQueueMetaProduct(int id) throws RemoteException;
	
        boolean removeResource(String user, String password, int id, boolean meta) throws RemoteException;
        boolean removeBook(String user, String password, int id) throws RemoteException;
        boolean removeMetaResource(String user, String password, int id) throws RemoteException;
        
}
