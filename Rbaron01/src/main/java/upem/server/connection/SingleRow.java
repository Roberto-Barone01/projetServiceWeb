
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

    void put(String k, String a){
        map.put(k, a);
    }
    
    public Map<String,String> get(){
        return this.map;
    }
    
}
