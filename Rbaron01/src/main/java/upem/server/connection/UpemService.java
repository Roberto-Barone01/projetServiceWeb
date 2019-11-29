package upem.server.connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import upem.shared.interfaces.BookProperty;
import upem.shared.interfaces.MetaProperty;

import upem.shared.interfaces.UpemServiceRequestable;

public class UpemService extends UnicastRemoteObject implements UpemServiceRequestable{

	private final QueryRequestable dbop = new DBOp();
	
	UpemService() throws RemoteException{
		super();
	}

	@Override
	public UpemResponse getResources(boolean meta) throws RemoteException, SQLException {
		UpemResponseImp resp = new UpemResponseImp(); 
		
		if(meta) 
			resp = dbop.meta();
		else
			resp = dbop.books();
		
		if(resp.result().size()>0)
			resp.setCode(UpemServiceRequestable.NO_SUCH_RESOURCE);
		else
			resp.setCode(UpemServiceRequestable.REQUEST_OK);
		
		return resp;
	}

	@Override
	public UpemResponse getAllBooks() throws RemoteException, SQLException {
		return getResources(false);
		
	}

	@Override
	public UpemResponse getAllMeta() throws RemoteException, SQLException {
		
		return getResources(true);
	}

	@Override
	public UnaryUpemResponse getInfofResource(boolean meta, int id) throws RemoteException, SQLException {
		UnaryUpemResponseImp resp = new UnaryUpemResponseImp();
		
		resp = dbop.info_resource(meta, id);

		if(resp.result() == null) 
			resp.setCode(UpemServiceRequestable.NO_SUCH_RESOURCE);
		else
			resp.setCode(UpemServiceRequestable.REQUEST_OK);
		
		return null;
	}

	@Override
	public UnaryUpemResponse getInfofBook(int id) throws RemoteException, SQLException {
		return getInfofResource(false, id);
	}

	@Override
	public UnaryUpemResponse getInfoOfMeta(int id) throws RemoteException, SQLException {
		return getInfofResource(true, id);
	}

	@Override
	public UnaryUpemResponse tryToGetResource(String user, String password, boolean meta, int id, boolean addMe)
			throws RemoteException, SQLException {

		int idUser = dbop.id_user(user);

		if (idUser == 0)
			return new UnaryUpemResponseImp(UpemServiceRequestable.NO_SUCH_USER);

		String passFinded = dbop.password(idUser);

		if (!password.equals(passFinded))
			return new UnaryUpemResponseImp(UpemServiceRequestable.INCORRECT_PASSWORD);

		if (meta) {
			if (dbop.meta_served(id)) {
				if (addMe) {
					dbop.add_user_to_queue_meta(id, idUser);
					return new UnaryUpemResponseImp(UpemServiceRequestable.ADD_TO_QUEUE);
				} else
					return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_NOT_DISPONIBLE);
			} else {
				dbop.reserve_meta_to_user(id, idUser);
				return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_OK);
			}
		} else {
			if (dbop.book_served(id)) {
				if (addMe) {
					dbop.add_user_to_queue_book(id, idUser);
					return new UnaryUpemResponseImp(UpemServiceRequestable.ADD_TO_QUEUE);
				} else
					return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_NOT_DISPONIBLE);
			} else {
				dbop.reserve_book_to_user(id, idUser);
				return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_OK);
			}

		}

	}

	@Override
	public UnaryUpemResponse tryToGetBook(String user, String password, int id, boolean addMe) throws RemoteException, SQLException {
		return tryToGetResource(user, password, false, id,addMe);
	}

	@Override
	public UnaryUpemResponse tryToGetMeta(String user, String password, int id, boolean addMe) throws RemoteException, SQLException {
		return tryToGetResource(user, password, false, id,addMe);
	}

	@Override
	public UnaryUpemResponse removeMeQueueResource(String User, String password, boolean meta, int id)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse removeMeQueueBook(String User, String password, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse removeMeQueueMeta(String User, String password, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse showQueueResources(boolean meta, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse showQueueBook(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse showQueueMeta(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpemResponse showMyQueues(String user, String password, boolean meta) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpemResponse showMyQueuesBook(String user, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpemResponse showMyQueuesMeta(String user, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpemResponse showMyResouces(String user, String password, boolean meta) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpemResponse showMyBooks(String user, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpemResponse showMyMetas(String user, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse removeResource(String user, String password, int id, boolean meta) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse removeBook(String user, String password, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse removeMeta(String user, String password, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse addBook(String user, String password, BookProperty book) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookProperty initialiseBook() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnaryUpemResponse addMetaResource(String user, String password, MetaProperty meta) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaProperty initialiseMeta(String user, String Password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

    
}
