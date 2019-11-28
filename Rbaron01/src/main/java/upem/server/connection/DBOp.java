package upem.server.connection;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;
import upem.shared.interfaces.BookProperty;
import static upem.shared.interfaces.UpemServiceRequestable.ID_BOOK_MISSING;

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
    
    public void addBook(Book book ) throws RemoteException, SQLException{
        
        String year = book.getYear();
        String edition = book.getEdition();
        String title = book.getTitle();
        String publisher = book.getPublisher();
        int pages = book.getPages();
        String isbn = book.getIsbn();
        String comment = book.getComment();
        String state = book.getState();
        double price = book.getPrice();
        
        Connection conn = connection();
        
        String inser = "INSERT INTO book"
                + "(edition,year,title,publisher,pages,isbn,comment,state,price)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement stm = conn.prepareStatement(inser);
        
        stm.setString(1, edition);
        stm.setString(2, year);
        stm.setString(3, title);
        stm.setString(4, publisher);
        stm.setInt(5, pages);
        stm.setString(6, isbn);
        stm.setString(7, comment);
        stm.setString(8, state);
        stm.setDouble(9, price);
            
        stm.executeUpdate();
        stop(conn);
        
        
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
            
            SingleRow row = new SingleRow(new HashMap<String,String>());
            
            if(meta){
                row.put("id",risQuery.getString("id"));
                row.put("meta_type", risQuery.getString("meta_type"));
                row.put("meta_name", risQuery.getString("meta_name"));
                row.put("state", risQuery.getString("state"));
                row.put("comment", risQuery.getString("comment"));
                
            }else{
                row.put("id",risQuery.getString("id"));
                row.put("title", risQuery.getString("title"));
                row.put("isbn", risQuery.getString("isbn"));
                row.put("pages", risQuery.getString("pages"));
                row.put("publisher", risQuery.getString("publisher"));
                row.put("year", risQuery.getString("year"));
                row.put("state", risQuery.getString("state"));
                row.put("comment", risQuery.getString("comment"));
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
    Il retourne les info d'un produit en donnent l'id
    @return string avec les info ou null s'il n'existe aucine produit avec l'id donné
    */
    public UnaryUpemResponseImp info(boolean meta, int id) throws SQLException{
        UnaryUpemResponseImp risp = new UnaryUpemResponseImp() {};
        Map<String,String> map = new HashMap<String,String>();
        
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
                
                map.put("username", risQ.getString("username"));
                map.put("date", risQ.getString("date"));
                map.put("meta_name", risQ.getString("meta_name"));
                map.put("meta_type", risQ.getString("meta_type"));

            }else{
                
                map.put("username", risQ.getString("username"));
                map.put("date", risQ.getString("date"));
                map.put("title", risQ.getString("title"));
                map.put("isbn", risQ.getString("isbn"));
                map.put("state", risQ.getString("state"));
                map.put("publisher", risQ.getString("publisher"));
            }
            
            risp.add(new SingleRow(map));
            stop(conn);
            return risp;
        }
        stop(conn);
        
        return new UnaryUpemResponseImp(ID_BOOK_MISSING);
        
    }
    
    public UnaryUpemResponseImp infoBook(int id) throws SQLException{
        return info(false,id);
    }
    
    public UnaryUpemResponseImp infoMeta(int id) throws SQLException{
        return info(true,id);
    }
    
    public boolean resource_served(int id, boolean meta) throws SQLException{
        Connection conn = connection();
        
        String query = "";
        
        if(meta)
            query = "SELECT meta_resource.id FROM meta_resource, user_add_meta_resource" +
                    "WHERE meta_resource.id = user_add_meta_resource.id_meta_resource AND meta_resource.id = ? AND " +
                    "user_add_meta_resource.state IS 'reserved'";
        else
            query = "SELECT book.id FROM book, user_add_book" +
                    "WHERE book.id = user_add_book.id_book AND book.id  = ? AND " +
                    "user_add_book.state IS 'reserved'";
        
        PreparedStatement stm = conn.prepareStatement(query);
        stm.setInt(1, id);
        
        ResultSet result = stm.executeQuery();
        
        
        boolean ris = false;
        if(result.next())
            ris = true;
        
        stop(conn);
        return ris;
    }
    
    public boolean book_served(int id, boolean meta) throws SQLException{
        return resource_served(id, false);
    }
    
    public boolean meta_served(int id, boolean meta) throws SQLException{
        return resource_served(id, true);
    }
}
