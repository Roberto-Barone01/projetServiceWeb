
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

public class MainClient {
    
    
    public static void main(String args[]){
        
        try {
            
            UpemServiceRequestable req = (UpemServiceRequestable) Naming.lookup("rmi://localhost:4499/upemService");
            /*
            UpemResponse risp = req.getAllMeta();
            if(risp.result().size()>0){
               ArrayList<SingleRow> rows = risp.result();
                for(int i=0;i<rows.size();i++){
                    System.out.println(rows.get(i).get().remove("Type"));
                    
                }
            }
            */
//            BookProperty book = req.initialiseBook();
//            book.title("Bella Cioa").publisher("No one").price(0).comment("Ciao");
//            req.addBook(null, null, book);
//            
        
            
           BookProperty book;
		try {
			book = req.initialiseBook();
			book.title("the lord of the rings").comment("Bello").edition("press").price(56);
			req.addBook("", "", book);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
        
        } catch (NotBoundException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
