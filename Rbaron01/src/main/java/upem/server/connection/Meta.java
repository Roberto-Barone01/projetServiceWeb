
package upem.server.connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import upem.shared.interfaces.MetaProperty;

public class Meta extends UnicastRemoteObject implements MetaProperty {
    
    
    String meta_type;
    String meta_name;
    String state;
    String comment;
    double price;
    
    Meta() throws RemoteException{
        super();
    }

    public MetaProperty meta_type(String meta_type) throws RemoteException {
        this.meta_type = meta_type;
        return this;
    }

    public MetaProperty meta_name(String meta_name) throws RemoteException {
        this.meta_name = meta_name;
        return this;
    }

    public MetaProperty state(String state) throws RemoteException {
        this.state = state;
        return this;
    }

    public MetaProperty comment(String comment) throws RemoteException {
        this.comment = comment;
        return this;
    }

    public MetaProperty price(double price) throws RemoteException {
        this.price = price;
        return this;
    }

}
