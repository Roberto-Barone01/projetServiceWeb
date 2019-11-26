package upem.server.connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import upem.shared.interfaces.UpemServiceRequestable;

public class UpemService extends UnicastRemoteObject implements UpemServiceRequestable{

	private final DBOp qm = new DBOp();
	private UserDate user;
	
	UpemService() throws RemoteException{
		super();
	}
	
        
        /* Il ajoute un user qui commente sa session
        @param user qui commente sa session
        */
	
	void addUser(UserDate user) {
		this.user = user;
		
	}
        
        
        /*
        Il contrôle si l'user a commence sa propre session
        @return true si l'user a commente sa prore sessione, falsa si l'user n'a pas commente sa session
        */
	
	boolean check(){
            if(user== null)
		return false;
            return true;
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
	public String getDatesOfMetaProduct(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryToGetResource(boolean meta, String user, int id, boolean addMe) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryToGetBook(String user, int id, boolean addMe) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryToGetMetaProduct(String user, int id, boolean addMe) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> showQueueResources(boolean meta, int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> showQueueBook(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> showQueueMetaProduct(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int myId() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isConnected() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

    public void removeMeQueueResource(boolean meta, int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeMeQueueBook(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeMeQueueMetaProduct(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeResource(int id, boolean meta) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeBook(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeMetaResource(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
