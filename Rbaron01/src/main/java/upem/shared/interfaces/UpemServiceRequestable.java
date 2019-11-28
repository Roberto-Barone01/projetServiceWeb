package upem.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;


public interface UpemServiceRequestable extends Remote{
    
    int SERVER_ERROR = 1000;
    int ID_BOOK_MISSING = 1001;
    
        interface UpemResponse extends Remote{
            public int code() throws RemoteException;
            public ArrayList<SingleRow> result() throws RemoteException;
        }
        
        interface UnaryUpemResponse extends Remote{
            int code() throws RemoteException;
            SingleRow result() throws RemoteException;

        }
        
        interface SingleRow extends Remote{
            Map<String,String> get() throws RemoteException;
        }
	
	UpemResponse getResources(boolean meta) throws RemoteException;
	UpemResponse getAllBooks() throws RemoteException;
        UpemResponse getAllMeta() throws RemoteException;
	
	UnaryUpemResponse getInfofResource(boolean meta, int id) throws RemoteException;
	UnaryUpemResponse getInfofBook(int id) throws RemoteException;
	UnaryUpemResponse getInfoOfMeta(int id) throws RemoteException;
	
	UnaryUpemResponse tryToGetResource(String user, String password, boolean meta, int id, boolean addMe) throws RemoteException;
	UnaryUpemResponse tryToGetBook(String user, String password, int id, boolean addMe) throws RemoteException;
	UnaryUpemResponse tryToGetMeta(String user,String password, int id, boolean addMe) throws RemoteException;

	UnaryUpemResponse removeMeQueueResource(String User, String password, boolean meta, int id) throws RemoteException;
	UnaryUpemResponse removeMeQueueBook(String User, String password, int id) throws RemoteException;
	UnaryUpemResponse removeMeQueueMeta(String User, String password, int id) throws RemoteException;
	
	UnaryUpemResponse showQueueResources(boolean meta, int id) throws RemoteException;
	UnaryUpemResponse showQueueBook(int id) throws RemoteException;
	UnaryUpemResponse showQueueMeta(int id) throws RemoteException;
	
        UpemResponse showMyQueues(String user, String password, boolean meta) throws RemoteException;
        UpemResponse showMyQueuesBook(String user, String password) throws RemoteException;
        UpemResponse showMyQueuesMeta(String user, String password) throws RemoteException;
        
        UpemResponse showMyResouces(String user, String password, boolean meta) throws RemoteException;
        UpemResponse showMyBooks(String user, String password) throws RemoteException;
        UpemResponse showMyMetas(String user, String password) throws RemoteException;
        
        UnaryUpemResponse removeResource(String user, String password, int id, boolean meta) throws RemoteException;
        UnaryUpemResponse removeBook(String user, String password, int id) throws RemoteException;
        UnaryUpemResponse removeMeta(String user, String password, int id) throws RemoteException;
        
        
        UnaryUpemResponse addBook(String user, String password, BookProperty book ) throws RemoteException;
        BookProperty initialiseBook() throws RemoteException;
      
        UnaryUpemResponse addMetaResource(String user, String password, MetaProperty meta) throws RemoteException;
        MetaProperty initialiseMeta(String user, String Password) throws RemoteException;       
}