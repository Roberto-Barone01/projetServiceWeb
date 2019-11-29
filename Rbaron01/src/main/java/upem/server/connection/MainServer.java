package upem.server.connection;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import upem.server.connection.*;
import upem.server.entryGen.Gen100;

public class MainServer {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
	UpemConnection conn = RMIUPEMServiceManager.startConnection();

	}

}
