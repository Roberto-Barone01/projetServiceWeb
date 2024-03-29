
package upem.client;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import upem.shared.interfaces.BookProperty;
import upem.shared.interfaces.UpemServiceRequestable;
import upem.shared.interfaces.UpemServiceRequestable.SingleRow;
import upem.shared.interfaces.UpemServiceRequestable.UpemResponse;
import upem.shared.interfaces.UpemServiceRequestable.UnaryUpemResponse;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;



public class MainClient {
    
    
    public static void main(String args[]){
        
        try {
         
            UpemServiceRequestable req = (UpemServiceRequestable) Naming.lookup("rmi://localhost:4499/upemService");
            //System.out.println(req.tryToGetBook("root", "root", 340, true).code());
            //System.out.println(req.removeMeQueueMeta("root", "root", 10).code());
            
//            BookProperty book = req.initialiseBook();
//            book.title("The lord of the rings").price(34).edition("Seconda")
//            .isbn("DKDNDKND");
//            req.addBook("root", "root", book);
//            
            //System.out.println(req.book_client());
            String root = "root";
            //System.out.println(req.add_to_panier(root, root, 16));
            //System.out.println(req.buy("root", "root", 30));
            
            //req.showMyBooks("root","root");
            //System.out.println(req.showMyQueues(root,root,false).result());
            //req.showResourceLent(root,root,true);
            
            req.return_meta(root, root, 2);
        } catch (NotBoundException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
