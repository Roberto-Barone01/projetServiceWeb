
package upem.server.connection;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import upem.shared.interfaces.UpemServiceRequestable;


class UnaryUpemResponseImp implements UpemServiceRequestable.UnaryUpemResponse, Serializable {

    private int code;
    private SingleRow ris;
    
    UnaryUpemResponseImp(){
    	super();
    }
    
    UnaryUpemResponseImp(int code){
        this.code = code;
    }
    
    public int code() throws RemoteException {
        return this.code;
    }

    void add(SingleRow sr){
        this.ris = sr;
    }

    public UpemServiceRequestable.SingleRow result() throws RemoteException {
        return this.ris;
    }

	public void setCode(int code) {
		this.code = code;
	}
	
	public String toString() {
		return ""+code;
	}

}