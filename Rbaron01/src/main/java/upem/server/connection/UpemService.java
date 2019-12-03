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
		
		if(resp.result().size() == 0)
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
		
		return resp;
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

		if (idUser == -1)
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
		return tryToGetResource(user, password, true, id,addMe);
	}

	@Override
	public UnaryUpemResponse removeMeQueueBook(String user, String password, int id_book) throws RemoteException, SQLException {
		int idUser = dbop.id_user(user);
		System.out.println(idUser);
		if (idUser == -1)
			return new UnaryUpemResponseImp(UpemServiceRequestable.NO_SUCH_USER);

		String passFinded = dbop.password(idUser);

		if (!password.equals(passFinded))
			return new UnaryUpemResponseImp(UpemServiceRequestable.INCORRECT_PASSWORD);
		
		dbop.remove_from_queue_book(idUser, id_book);
		return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_OK);
	}

	@Override
	public UnaryUpemResponse removeMeQueueMeta(String user, String password, int id_meta) throws RemoteException, SQLException {
		int idUser = dbop.id_user(user);

		if (idUser == -1)
			return new UnaryUpemResponseImp(UpemServiceRequestable.NO_SUCH_USER);

		String passFinded = dbop.password(idUser);

		if (!password.equals(passFinded))
			return new UnaryUpemResponseImp(UpemServiceRequestable.INCORRECT_PASSWORD);
		
		dbop.remove_from_queue_meta_resource(idUser, id_meta);
		return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_OK);
	}

	@Override
	public UpemResponse showQueueResources(boolean meta, int id_resource) throws RemoteException, SQLException {
		
		UpemResponseImp risp;
		if(meta)
			risp = dbop.show_queue_of_meta(id_resource);
		else
			risp = dbop.show_queue_of_book(id_resource) ;
		
		if(risp.result().size() > 0)
			risp.setCode(UpemServiceRequestable.REQUEST_OK);
		else
			risp.setCode(UpemServiceRequestable.NO_SUCH_RESOURCE);
		
		return risp;
	}

	@Override
	public UpemResponse showQueueBook(int id_book) throws RemoteException, SQLException {
		return showQueueResources(false,id_book);
	}

	@Override
	public UpemResponse showQueueMeta(int id_meta) throws RemoteException, SQLException {
		return showQueueResources(true, id_meta);
	}

	@Override
	public UpemResponse showMyQueues(String user, String password) throws RemoteException, SQLException {
		
		int idUser = dbop.id_user(user);

		if (idUser == -1)
			return new UpemResponseImp(UpemServiceRequestable.NO_SUCH_USER);

		String passFinded = dbop.password(idUser);

		if (!password.equals(passFinded))
			return new UpemResponseImp(UpemServiceRequestable.INCORRECT_PASSWORD);
		
		UpemResponseImp risp = dbop.show_all_queue_user(idUser);
		risp.setCode(UpemServiceRequestable.REQUEST_OK);
		return risp;
	}



	@Override
	public UpemResponse showMyResouces(String user, String password, boolean meta) throws RemoteException, SQLException {
		int idUser = dbop.id_user(user);

		if (idUser == -1)
			return new UpemResponseImp(UpemServiceRequestable.NO_SUCH_USER);

		String passFinded = dbop.password(idUser);
		
		if (!password.equals(passFinded))
			return new UpemResponseImp(UpemServiceRequestable.INCORRECT_PASSWORD);
		
		UpemResponseImp risp = new UpemResponseImp();
		if(meta)
			risp = dbop.show_meta_resource_add_by_user(idUser);
		else
			risp = dbop.show_book_add_by_user(idUser);
		risp.setCode(UpemServiceRequestable.REQUEST_OK);
		
		return null;
		
	}

	@Override
	public UpemResponse showMyBooks(String user, String password) throws RemoteException, SQLException {
		return showMyResouces(user, password, false);
	}

	@Override
	public UpemResponse showMyMetas(String user, String password) throws RemoteException, SQLException {
		return showMyResouces(user, password, true);
	}

	@Override
	public UnaryUpemResponse removeResource(String user, String password, int id, boolean meta) throws RemoteException, SQLException {
		int idUser = dbop.id_user(user);

		if (idUser == -1)
			return new UnaryUpemResponseImp(UpemServiceRequestable.NO_SUCH_USER);
		String passFinded = dbop.password(idUser);
		if (!password.equals(passFinded))
			return new UnaryUpemResponseImp(UpemServiceRequestable.INCORRECT_PASSWORD);

		if(meta)
			dbop.remove_meta_resource(id);
		else
			dbop.remove_book(id);
		
		return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_OK);
	}

	@Override
	public UnaryUpemResponse removeBook(String user, String password, int id) throws RemoteException, SQLException {
		return removeResource(user, password, id, false);
	}

	@Override
	public UnaryUpemResponse removeMeta(String user, String password, int id) throws RemoteException, SQLException {
		return removeResource(user, password, id, true);

	}

	@Override
	public UnaryUpemResponse addBook(String user, String password, BookProperty book) throws RemoteException, SQLException {
		int idUser = dbop.id_user(user);

		if (idUser == -1)
			return new UnaryUpemResponseImp(UpemServiceRequestable.NO_SUCH_USER);
		String passFinded = dbop.password(idUser);
		if (!password.equals(passFinded))
			return new UnaryUpemResponseImp(UpemServiceRequestable.INCORRECT_PASSWORD);
		
		dbop.addBook(book, idUser);
		
		return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_OK);
	}

	@Override
	public BookProperty initialiseBook() throws RemoteException {
		return new Book();
	}

	@Override
	public UnaryUpemResponse addMetaResource(String user, String password, MetaProperty meta) throws RemoteException, SQLException {
		int idUser = dbop.id_user(user);

		if (idUser == -1)
			return new UnaryUpemResponseImp(UpemServiceRequestable.NO_SUCH_USER);
		String passFinded = dbop.password(idUser);
		if (!password.equals(passFinded))
			return new UnaryUpemResponseImp(UpemServiceRequestable.INCORRECT_PASSWORD);
		
		dbop.addMeta(meta, idUser);
		
		return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_OK);
	}

	@Override
	public MetaProperty initialiseMeta(String user, String Password) throws RemoteException {
		return new Meta();
	}

	@Override
	public UnaryUpemResponse return_resource(String user, String password, int id_resource, boolean meta)
			throws RemoteException, SQLException {
	
		int idUser = dbop.id_user(user);

		if (idUser == -1)
			return new UnaryUpemResponseImp(UpemServiceRequestable.NO_SUCH_USER);
		String passFinded = dbop.password(idUser);
		if (!password.equals(passFinded))
			return new UnaryUpemResponseImp(UpemServiceRequestable.INCORRECT_PASSWORD);
		
		dbop.add_complete_status_request(idUser, id_resource, meta);
		return new UnaryUpemResponseImp(UpemServiceRequestable.REQUEST_OK);
		
	}

	@Override
	public UnaryUpemResponse return_book(String user, String password, int id_resource)
			throws RemoteException, SQLException {
		return return_resource(user, password, id_resource, false);
	}

	@Override
	public UnaryUpemResponse return_meta(String user, String password, int id_resource)
			throws RemoteException, SQLException {
		return return_resource(user, password, id_resource, true);
	}    
}
