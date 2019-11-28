
package upem.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import upem.shared.interfaces.UpemServiceRequestable.UpemResponse;

public interface BookProperty extends Remote {
    
    BookProperty title(String title) throws RemoteException;
    BookProperty publisher(String publisher) throws RemoteException;
    BookProperty year(String year) throws RemoteException;
    BookProperty pages(int pages) throws RemoteException;
    BookProperty isbn(String isbn) throws RemoteException;
    BookProperty comment(String commento) throws RemoteException;
    BookProperty state(String state) throws RemoteException;
    BookProperty price(double price) throws RemoteException;
    BookProperty edition(String edition) throws RemoteException;
    
    public String getTitle() throws RemoteException;
    public String getPublisher() throws RemoteException;
    public int getPages() throws RemoteException;
    public String getIsbn() throws RemoteException;
    public String getComment() throws RemoteException;
    public String getState() throws RemoteException;
    public double getPrice() throws RemoteException;
    public String getEdition() throws RemoteException;
    public String getYear() throws RemoteException;

}
