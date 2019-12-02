
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
    String getEdition() throws RemoteException;
    String getYear() throws RemoteException;
    String getTitle()  throws RemoteException;
    String getPublisher() throws RemoteException;
    int getPages() throws RemoteException;
    String getIsbn() throws RemoteException;
    String getComment() throws RemoteException;
    String getState() throws RemoteException;
    double getPrice() throws RemoteException;

}
