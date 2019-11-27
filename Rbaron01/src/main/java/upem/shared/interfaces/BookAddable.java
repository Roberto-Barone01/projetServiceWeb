
package upem.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import upem.shared.interfaces.UpemServiceRequestable.UpemResponse;

public interface BookAddable extends Remote {
    
    BookAddable title(String title) throws RemoteException;
    BookAddable publisher(String publisher) throws RemoteException;
    BookAddable year(String year) throws RemoteException;
    BookAddable pages(int pages) throws RemoteException;
    BookAddable isbn(String isbn) throws RemoteException;
    BookAddable comment(String commento) throws RemoteException;
    BookAddable state(String state) throws RemoteException;
    BookAddable price(double price) throws RemoteException;
    BookAddable edition(String edition) throws RemoteException;
    
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
