package upem.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public interface UpemServiceRequestable extends Remote {

	int REQUEST_OK = 999;
	int SERVER_ERROR = 1000;
	int ID_BOOK_MISSING = 1001;
	int NO_SUCH_RESOURCE = 1002;
	int NO_SUCH_USER = 1003;
	int INCORRECT_PASSWORD = 1004;
	int ADD_TO_QUEUE = 1005;
	int REQUEST_NOT_DISPONIBLE = 1006;

	interface UpemResponse extends Remote {
		public int code() throws RemoteException;
		public ArrayList<SingleRow> result() throws RemoteException;
	}

	interface UnaryUpemResponse extends Remote {
		int code() throws RemoteException;
		SingleRow result() throws RemoteException;
	}

	interface SingleRow extends Remote {
		Map<String, String> get() throws RemoteException;
	}

	UpemResponse getResources(boolean meta) throws RemoteException, SQLException;

	UpemResponse getAllBooks() throws RemoteException, SQLException;

	UpemResponse getAllMeta() throws RemoteException, SQLException;

	UnaryUpemResponse getInfofResource(boolean meta, int id) throws RemoteException, SQLException;

	UnaryUpemResponse getInfofBook(int id) throws RemoteException, SQLException;

	UnaryUpemResponse getInfoOfMeta(int id) throws RemoteException, SQLException;

	UnaryUpemResponse tryToGetResource(String user, String password, boolean meta, int id, boolean addMe)
			throws RemoteException, SQLException;

	UnaryUpemResponse tryToGetBook(String user, String password, int id_book, boolean addMe) throws RemoteException, SQLException;

	UnaryUpemResponse tryToGetMeta(String user, String password, int id_meta, boolean addMe) throws RemoteException, SQLException;

	UnaryUpemResponse removeMeQueueBook(String User, String password, int id_book) throws RemoteException, SQLException;

	UnaryUpemResponse removeMeQueueMeta(String User, String password, int id_meta) throws RemoteException, SQLException;

	UpemResponse showQueueResources(boolean meta, int id) throws RemoteException, SQLException;

	UpemResponse showQueueBook(int id) throws RemoteException, SQLException;

	UpemResponse showQueueMeta(int id) throws RemoteException, SQLException;

	UpemResponse showMyQueues(String user, String password) throws RemoteException, SQLException;

	UpemResponse showMyResouces(String user, String password, boolean meta) throws RemoteException, SQLException;

	UpemResponse showMyBooks(String user, String password) throws RemoteException, SQLException;

	UpemResponse showMyMetas(String user, String password) throws RemoteException, SQLException;

	UnaryUpemResponse removeResource(String user, String password, int id, boolean meta) throws RemoteException, SQLException;

	UnaryUpemResponse removeBook(String user, String password, int id) throws RemoteException, SQLException;

	UnaryUpemResponse removeMeta(String user, String password, int id) throws RemoteException, SQLException;

	UnaryUpemResponse addBook(String user, String password, BookProperty book) throws RemoteException, SQLException;

	BookProperty initialiseBook() throws RemoteException, SQLException;

	UnaryUpemResponse addMetaResource(String user, String password, MetaProperty meta) throws RemoteException, SQLException;

	MetaProperty initialiseMeta(String user, String Password) throws RemoteException, SQLException;
	
	UnaryUpemResponse return_resource(String user, String password, int id_resource, boolean meta) throws RemoteException, SQLException;
	
	UnaryUpemResponse return_book(String user, String password, int id_resource) throws RemoteException, SQLException;
	
	UnaryUpemResponse return_meta(String user, String password, int id_resource) throws RemoteException, SQLException;
}