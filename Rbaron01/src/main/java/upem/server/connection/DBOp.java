package upem.server.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBOp {
		
    private final String nameDb="DBServiceWeb.db";
    private final String path="C:/sqlite/db";
    //name driver
    private final String nameD="jdbc:sqlite:";
    
    private Connection connection() throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection(nameD+path+nameDb);
        return conn;
    }
    
    private void stop(Connection conn) throws SQLException{
        conn.close();
    }
    
    public boolean test(){
        Connection c = null;
        try {
            c = connection();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally{
            try {
                stop(c);
            } catch (SQLException ex) {
                Logger.getLogger(DBOp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
