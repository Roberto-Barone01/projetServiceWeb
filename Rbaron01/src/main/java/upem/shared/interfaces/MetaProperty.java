
package upem.shared.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface MetaProperty extends Remote{
    MetaProperty meta_type(String meta_type) throws RemoteException;
    MetaProperty meta_name(String meta_name) throws RemoteException;
    MetaProperty state(String state) throws RemoteException;
    MetaProperty comment(String comment) throws RemoteException;
    MetaProperty price(double price) throws RemoteException;
}
