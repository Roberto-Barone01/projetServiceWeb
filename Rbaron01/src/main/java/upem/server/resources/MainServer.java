package upem.server.resources;

import java.rmi.RemoteException;


import upem.server.connection.*;

public class MainServer {

	public static void main(String[] args) throws RemoteException {
		
		UpemConnection conn = RMIUPEMServiceManager.startConnection();
                System.out.println(new DBOp().test());
	}

}
