package upem.server.resources;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import upem.server.connection.*;
import uper.server.entryGen.Gen100;

public class MainServer {

	public static void main(String[] args) throws RemoteException {
		
	//UpemConnection conn = RMIUPEMServiceManager.startConnection();
            try {
                new Gen100().metaResource();
            } catch (SQLException ex) {
                Logger.getLogger(MainServer.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}
