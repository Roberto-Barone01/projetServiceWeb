package upem.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;


public interface UpemServiceRequestable extends Remote{
    
    
        interface UpemResponse extends Remote{
            
            
            int SEVER_ERROR = 1000;
            
            public int code() throws RemoteException;
            public ArrayList<SingleRow> result() throws RemoteException;
        }
        
        interface SingleRow extends Remote{
            Map<String,String> get() throws RemoteException;
        }
	
	UpemResponse getResources(boolean meta) throws RemoteException;
	UpemResponse getAllBooks() throws RemoteException;
        UpemResponse getAllMeta() throws RemoteException;
	
	UpemResponse getInfofResource(boolean meta, int id) throws RemoteException;
	UpemResponse getInfofBook(int id) throws RemoteException;
	UpemResponse getInfoOfMeta(int id) throws RemoteException;
	
	boolean tryToGetResource(String user, String password, boolean meta, int id, boolean addMe) throws RemoteException;
	boolean tryToGetBook(String user, String password, int id, boolean addMe) throws RemoteException;
	boolean tryToGetMeta(String user,String password, int id, boolean addMe) throws RemoteException;
	
	
	boolean removeMeQueueResource(String User, String password, boolean meta, int id) throws RemoteException;
	boolean removeMeQueueBook(String User, String password, int id) throws RemoteException;
	boolean removeMeQueueMeta(String User, String password, int id) throws RemoteException;
	
	UpemResponse showQueueResources(boolean meta, int id) throws RemoteException;
	UpemResponse showQueueBook(int id) throws RemoteException;
	UpemResponse showQueueMeta(int id) throws RemoteException;
	
        boolean removeResource(String user, String password, int id, boolean meta) throws RemoteException;
        boolean removeBook(String user, String password, int id) throws RemoteException;
        boolean removeMeta(String user, String password, int id) throws RemoteException;
        
        
        UpemResponse addBook(String user, String password, BookAddable book ) throws RemoteException;
        BookAddable initialiseBook() throws RemoteException;
        
        
        boolean addMetaResource(String user, String password, int id) throws RemoteException;
        
        
}
