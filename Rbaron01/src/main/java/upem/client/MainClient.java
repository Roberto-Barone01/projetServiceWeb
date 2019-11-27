
package upem.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import upem.shared.interfaces.UpemServiceRequestable;

public class MainClient {
    
    
    public static void main(String args[]){
        
        try {
            
            UpemServiceRequestable req = (UpemServiceRequestable) Naming.lookup("rmi://localhost:4499/upemService");
        
        
        } catch (NotBoundException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
