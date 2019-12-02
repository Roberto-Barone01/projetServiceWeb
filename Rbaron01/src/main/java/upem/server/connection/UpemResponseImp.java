
package upem.server.connection;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;
import upem.shared.interfaces.UpemServiceRequestable;

public class UpemResponseImp implements UpemServiceRequestable.UpemResponse, Serializable {

    private int code;
    ArrayList<UpemServiceRequestable.SingleRow> ris;
    
    
    UpemResponseImp(){
        this.ris = new ArrayList<UpemServiceRequestable.SingleRow>();
    }
    
    UpemResponseImp(int code){
        this.code = code;
    }
    
    public void setCode(int code) {
    	this.code = code;
    }
    
    public int code() throws RemoteException {
        return code;
    }

    public void add(SingleRow sr){
        this.ris.add(sr);
    }

    public ArrayList<UpemServiceRequestable.SingleRow> result() throws RemoteException {
        return this.ris;
    }
    
}
