package upem.server.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBOp {
		
    private final String nameDb="DBServiceWeb.db";
    private final String path="C:/sqlite/";
    //name driver
    private final String nameD="jdbc:sqlite:";
    
    public Connection connection() throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection(nameD+path+nameDb);
        return conn;
    }
    
    public void stop(Connection conn) throws SQLException{
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
    
    
    
    /* Il retourne la mot de passe de l'user 
    @param id -> id user 
    @return la mot de passe de l'user
    */
    
    public String password(int id) throws SQLException{
        
        Connection conn = connection();
        
        String query = "SELECT password FROM user WHERE id = ?";
        PreparedStatement stm = conn.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet risQuery = stm.executeQuery();
        String ris = null;
        if(risQuery.next())
            ris = risQuery.getString("password"); 
            
        stop(conn);
        return ris;
        
    }
    
    
    public UpemResponseImp resources(boolean meta) throws SQLException{
        
        UpemResponseImp risp = new UpemResponseImp();
        
        Connection conn = connection();
        String query = "";
        if(meta)
            query = "SELECT * FROM meta_resource WHERE deleted = false ";
        else
            query = "SELECT * FROM book WHERE deleted = false";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet risQuery = stm.executeQuery();


        while(risQuery.next()){
            
            SingleRow row = new SingleRow();
            
            if(meta){
                row.put("Type", risQuery.getString("meta_type"));
                row.put("Name", risQuery.getString("meta_name"));
                row.put("State", risQuery.getString("state"));
                row.put("Comment", risQuery.getString("comment"));
                
            }else{
                row.put("Title", risQuery.getString("title"));
                row.put("ISBN", risQuery.getString("isbn"));
                row.put("Pages", risQuery.getString("pages"));
                row.put("Publisher", risQuery.getString("publisher"));
                row.put("Year", risQuery.getString("year"));
                row.put("State", risQuery.getString("state"));
                row.put("Comment", risQuery.getString("comment"));
            }
            risp.add(row);

        }
        
        stop(conn);
        return risp;
            
    
    }
    
    public UpemResponseImp meta() throws SQLException{
        return resources(true);
    }
    
    public UpemResponseImp books() throws SQLException{
        return resources(false);
    }
    
    
    
    /*
    Il retourne les info d'un produit
    @return string avec les info ou null s'il n'existe aucine produit avec l'id donné
    */
    public String info(boolean meta, int id) throws SQLException{
        
        Connection conn = connection();
        String query = "";
        if(meta){
            query = "Select username, date, meta_name, meta_type FROM user,user_add_meta_resource, meta_resource "
            + "WHERE user.id = user_add_meta_resource.id_user AND " +
            "meta_resource.id = user_add_meta_resource.id_meta_resource AND meta_resource.id = ?";
        }else{
            query = "Select username, date, title, isbn, state, publisher FROM user,user_add_book, book " +
                    "WHERE user.id = user_add_book.id_user AND " +
                     "book.id = user_add_book.id_book AND book.id = ?";
        }
         
        PreparedStatement stm = conn.prepareStatement(query);
        stm.setInt(1, id);
        ResultSet risQ = stm.executeQuery();
        StringBuilder ris = new StringBuilder();
        if(risQ.next()){
            if(meta){
                ris.append("User: "+risQ.getString("username"));
                ris.append("date: "+risQ.getString("date"));
                ris.append("meta name: "+risQ.getString("meta_name"));
                ris.append("meta type: "+risQ.getString("meta_type"));
            }else{
                ris.append("User: "+risQ.getString("username"));
                ris.append("date: "+risQ.getString("date"));
                ris.append("Title: "+risQ.getString("title"));
                ris.append("ISBN: "+risQ.getString("isbn"));
                ris.append("State: "+risQ.getString("state"));
                ris.append("Publisher: "+risQ.getString("publisher"));
            }
            return ris.toString();
        }
        return null;
        
    }
    
    public String infoBook(int id) throws SQLException{
        return info(false,id);
    }
    
    public String infoMeta(int id) throws SQLException{
        return info(true,id);
    }
}
