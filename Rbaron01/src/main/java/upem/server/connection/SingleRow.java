
package upem.server.connection;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import upem.shared.interfaces.UpemServiceRequestable;

public class SingleRow implements Serializable, UpemServiceRequestable.SingleRow{
    
    Map<String,String> map;
    
    SingleRow(Map<String,String> map){
        this.map = map;
    }

    public SingleRow() {
		this.map = new HashMap<>();
	}

	void put(String k, String a){
        map.put(k, a);
    }
    
    public Map<String,String> get(){
        return this.map;
    }
    
    public String toString() {
    	return map.toString();
    }
    
}
