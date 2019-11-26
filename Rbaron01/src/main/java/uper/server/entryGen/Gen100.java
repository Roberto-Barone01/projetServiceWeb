
package uper.server.entryGen;

import com.github.javafaker.Faker;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import upem.server.connection.DBOp;

public class Gen100 {
    
    
    public static void gen() throws SQLException{
        Gen100.user();
        Gen100.book();
        Gen100.metaResource();
        
    }
    
    public static void book() throws SQLException{
        Faker f = new Faker();
        DBOp db = new DBOp();
        Connection conn = db.connection();
        
        String inser = "INSERT INTO book"
                + "(edition,year,title,publisher,pages,isbn,comment,state,deleted,price)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?) ";
        
        for(int i=0;i<100;i++){
            
            String edition = (i%2==0) ? "first":"second";
            String year = f.number().numberBetween(1968,2019)+"";
            String title = f.book().title();
            String publisher = f.book().publisher();
            int npages = f.number().numberBetween(30, 2500);
            String isbn = f.code().isbn10();
            String comment = f.yoda().quote();
            String state = (i%2==0) ? "new" : "used";
            boolean deleted = false;
            double price = f.number().randomDouble(3, 5, 100);
            
            PreparedStatement stm = conn.prepareStatement(inser);
            
            stm.setString(1, edition);
            stm.setString(2, year);
            stm.setString(3, title);
            stm.setString(4, publisher);
            stm.setInt(5, npages);
            stm.setString(6, isbn);
            stm.setString(7, comment);
            stm.setString(8, state);
            stm.setBoolean(9, deleted);
            stm.setDouble(10, price);
            
            stm.executeUpdate();
            
            String query = " select MAX(id) AS max FROM book";
            Statement stmQ = conn.createStatement();
            ResultSet ris = stmQ.executeQuery(query);
            ris.next();
            int idBook = ris.getInt("max");
            
            String queryUser = "SELECT id FROM BOOK ORDER BY RANDOM() LIMIT 1";
            Statement stmQuser = conn.createStatement();
            ResultSet risuser = stmQuser.executeQuery(queryUser);
            risuser.next();
            int idUser = risuser.getInt("id");
            
            String queryInserAddBook = " INSERT INTO user_add_book(id_user,id_book)"
                    + "VALUES(?,?)";
            PreparedStatement stmAddBook = conn.prepareStatement(queryInserAddBook);
            stmAddBook.setInt(1, idUser);
            stmAddBook.setInt(2,idBook);
            
            stmAddBook.executeUpdate();
            
        }
        
    }
    
    
    public static void metaResource() throws SQLException{
        Faker f = new Faker();
        DBOp db = new DBOp();
        Connection conn = db.connection();
        
        String inser = "INSERT INTO meta_resource"
                + "(meta_type,meta_name,state,comment, deleted)"
                + "VALUES(?,?,?,?,?) ";
        
        for(int i=0;i<100;i++){
            
            String meta_type = f.slackEmoji().objectsAndSymbols();
            String meta_name = f.slackEmoji().foodAndDrink();
            String state = (i%2==0) ? "new" : "used";
            String comment = f.yoda().quote();
            boolean deleted = false;
            
            PreparedStatement stm = conn.prepareStatement(inser);
            
            stm.setString(1,meta_type);
            stm.setString(2,meta_name);
            stm.setString(3,state);
            stm.setString(4,comment);
            stm.setBoolean(5,deleted);
            
            stm.executeUpdate();
            
            String query = " select MAX(id) AS max FROM meta_resource";
            Statement stmQ = conn.createStatement();
            ResultSet ris = stmQ.executeQuery(query);
            ris.next();
            int idMeta = ris.getInt("max");
            
            String queryUser = "SELECT id FROM BOOK ORDER BY RANDOM() LIMIT 1";
            Statement stmQuser = conn.createStatement();
            ResultSet risuser = stmQuser.executeQuery(queryUser);
            risuser.next();
            int idUser = risuser.getInt("id");
            
            String queryInserAddBook = " INSERT INTO user_add_meta_resource(id_user,id_meta_resource)"
                    + "VALUES(?,?)";
            PreparedStatement stmAddBook = conn.prepareStatement(queryInserAddBook);
            stmAddBook.setInt(1, idUser);
            stmAddBook.setInt(2,idMeta);
            
            stmAddBook.executeUpdate();
            
        }
        
    }
    
    public static void user() throws SQLException{
        
        Faker f = new Faker();
        DBOp db = new DBOp();
        Connection conn = db.connection();
            
        String type_1 = "student";
        String type_2= "professor";
             
        String insert = "INSERT INTO user(password,username,type) VALUES(?,?,?)";
            
        for(int i=0;i<100;i++){
            PreparedStatement stm = conn.prepareStatement(insert);
            stm.setString(1, f.internet().password((8), 50));
            stm.setString(2, f.name().username());
            
            if(i%2 == 0)
                stm.setString(3, type_1);
            else
                stm.setString(3, type_2);
            
            stm.executeUpdate();
        }
        
        db.stop(conn);
 
    }
        
}

