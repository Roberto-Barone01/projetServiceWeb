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

public class DBOp implements QueryRequestable {
		
    private final String nameDb="DBServiceWeb.db";
    private final String path="C:/sqlite/";
    //name driver
    private final String nameD="jdbc:sqlite:";
    
    
    /* Il crée une connection avec la base de donnees
     * @return l'objet qui rappresent la connection
     * @throws @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
    
    @Override
	public Connection connection() throws SQLException{
        Connection conn = null;
        conn = DriverManager.getConnection(nameD+path+nameDb);
        return conn;
    }
    
    @Override
	public void stop(Connection conn) throws SQLException{
        conn.close();
    }
    
    
    /* Il controle la connession avec la base de données.
     * Il faut l'utiliser seulment pour des opération de debug.
     * @param return true si la connection a eu succes, false au contraire
     */
    
    @Override
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
    
    
    /* On retourne l'id dans la table user de l'user qui corrispondent à les parametres
     * @param username de l'user
     * @param password pour acceder à les service offert par l'UPEM
     * @throws RemoteException
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
    
    
    @Override
	public int id_user(String user) {
    
    	
    	return 0;
    }
    
    
    
    /* 
    * Il retourne la mot de passe de l'user 
    * @param id -> id user 
    * @return la mot de passe de l'user
    */
    
    @Override
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
    
    
    /*
     * Il ajoute un nouveau livre dans la table book
     * @param le livre qu'on va ajouter
     * @throws RemoteException
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
    
    @Override
	public void addBook(Book book, int id_user) throws SQLException{
        
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
    
    /*
     * Il retourne toue les données relative à la table meta_resource ou book
     * @param flag qui dit si on veut la table book ou meta_resource (TRUE = meta_resource, FALSE = book)
     * @throws RemoteException
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     * @return UpemResponse avec tout les donnees dans la table book ou meta_resource
     */
    @Override
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
    
    /*
     * Il retourne toue les données relative à la table meta_resource
     * @throws RemoteException
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     * @return UpemResponse avec tout les donnees dans la table meta_resource
     */
    
    @Override
	public UpemResponseImp meta() throws SQLException{
        return resources(true);
    }
    
    /*
     * Il retourne toue les données relative à la table meta_resource
     * @throws RemoteException
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     * @return UpemResponse avec tout les donnees dans la table book
     */
    
    @Override
	public UpemResponseImp books() throws SQLException{
        return resources(false);
    }
    
    
    
    /*
    Il retourne tous les info d'un produit en donnent l'id
    @param boolean meta, qui nous dit s'il faut chercher dans la table meta resource ou book (TRUE = meta_resource, FALSE = book)
    @param id de la resource qu'on va chercher dans la table meta_resource ou book
    @return UnaryUpemResponse avec les données qui consiste de tous les info dans la table book ou meta et les info sur l'user qui a ajouté la resource
    et la date.
    */
    @Override
	public UnaryUpemResponseImp info_resource(boolean meta, int id) throws SQLException{
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
    
    /*
    * Il retourne tous les info d'un book en donnent un id
    * @param id de la resource qu'on va chercher dans la table  book
    * @return UnaryUpemResponse avec les données qui consiste de tous les info dans la table book et les info sur l'user qui a ajouté la resource
    * et la date.
    * @throws RemoteException
    * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
    */
    
    @Override
	public UnaryUpemResponseImp infoBook(int id) throws SQLException{
        return info_resource(false,id);
    }
    
    
    
    /*
    * Il retourne tous les info d'un meta_resource en donnent un id
    * @param id de la resource qu'on va chercher dans la table  meta_resource
    * @return UnaryUpemResponse avec les données qui consiste de tous les info dans la table meta_resource et les info sur l'user qui a ajouté la resource
    * et la date.
    * @throws RemoteException
    * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
    */
    
    @Override
	public UnaryUpemResponseImp infoMeta(int id) throws SQLException{
        return info_resource(true,id);
    }
    
    
    /*
     * Returne un boolean qui nous dit si la resource demandé n'est pas maintenent disponible
     * @param id resource
     * @param meta, qui nous dit si on va chercher la resource dans la table book ou meta_resource
     * @throws RemoteException
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
    
    @Override
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
    
    
    /*
     * Returne un boolean qui nous dit si le book demandé n'est pas maintenant disponible
     * @param id book
     * @throws RemoteException
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
   
    @Override
	public boolean book_served(int id) throws SQLException{
        return resource_served(id, false);
    }
    
    /*
     * Retourne un boolean qui nous dit si le book demandé n'est pas maintenant disponible
     * @param id book
     * @throws RemoteException
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
    
    @Override
	public boolean meta_served(int id) throws SQLException{
        return resource_served(id, true);
    }
    
    /*
     * On donne une resource à l'user qui corrisponde à id_user.
     * On va ajouter l'user e la resource demandé dans la table user_request_book uo user_request_meta_resource
     * avec status = reserved
     * @param id de la resource
     * @param flag qui nous dit si l'user ajoute un meta_resource ou book (TRUE = meta, FALSE = book)
     * @param id user
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
    
    @Override
	public boolean reserve_resource_to_user(int id_resource, boolean meta, int id_user ) throws SQLException{
    	return false;
    }
    
    /*
     * On donne une book à l'user qui corrisponde à id_user.
     * On va ajouter l'user e la resource demandé dans la table user_request_book
     * avec status = reserved
     * @param id book dans la table book
     * @param id user dans la table user
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
    
	@Override
	public boolean reserve_book_to_user(int id_book, int id_user ) throws SQLException{
			return reserve_resource_to_user(id_book, false, id_user);
	}
	
	/*
     * On donne un meta_resource à l'user qui corrisponde à id_user.
     * On va ajouter l'user e la resource demandé dans la table user_request_meta_resource
     * avec status = reserved
     * @param id book dans la table book
     * @param id user dans la table user
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
	
	@Override
	public boolean reserve_meta_to_user(int id_meta, int id_user ) throws SQLException{
		return reserve_resource_to_user(id_meta, true, id_user);
	}
	
	/*
     * On va ajouter l'user e la resource demandé dans la table user_request_book uo user_request_meta_resource
     * avec status = Attend
     * @param id de la resource
     * @param flag qui nous dit si l'user ajoute un meta_resource ou book (TRUE = meta, FALSE = book)
     * @param id user
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
    
    @Override
	public boolean add_user_to_queue(int id_resource, boolean meta, int id_user ) throws SQLException{
    	return false;
    	
    }
    
    /*
     * On va ajouter l'user e la resource demandé dans la table user_request_book
     * avec status = Attend
     * @param id book dans la table book
     * @param id user dans la table user
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
    
	@Override
	public boolean add_user_to_queue_book(int id_book, int id_user ) throws SQLException{
			return add_user_to_queue(id_book, false, id_user);
			
	}
	
	/*
     * On va ajouter l'user e la resource demandé dans la table user_request_meta_resource
     * avec status = Attend
     * @param id meta dans la table meta_resource
     * @param id user dans la table user
     * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
     */
	
	@Override
	public boolean add_user_to_queue_meta(int id_meta, int id_user ) throws SQLException{
		return add_user_to_queue(id_meta, true, id_user);
		
	}

	
	/*
	 * Il retourne tous les resource qui sont été demandé par l'user avec status attend
	 * @param id user
	 * @return UpemResponseImp qui contient tout le resource demandé par l'user
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */
    
	UpemResponseImp queue_user(int id) {
		return null;
	}
	
	/*
	 * Il ajoute une nouvelle meta resource dans la table meta et 
	 * l'information sur l'insertion dans user_add_meta_resource
	 * @param Meta qu'on va ajouter
	 * @param id user qui ajoute la meta resource
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */

	@Override
	public void addMeta(Meta meta, int id_user) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * On supprime la resource qui corrispond à l'id donné
	 * @param id resource
	 * @param flag qui nous dit si la resource est un meta ou book
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */

	@Override
	public boolean remove_resource(int id, boolean meta) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * On supprime le book qui corrispond à l'id donné
	 * @param id resource
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */

	@Override
	public boolean remove_book(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * On supprime la meta_resource qui corrispond à l'id donné
	 * @param id meta
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */

	@Override
	public boolean remove_meta_resource(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * On va supprimer par la queue la requeste de l'user s'il a status attend
	 * @param id user
	 * @param id_resource
	 * @param flag qui nous dit si la resource est un meta ou book
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */

	@Override
	public boolean remove_from_queue(int id_user, int id_resource, boolean meta) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * On va supprimer par la queue la requeste de l'user s'il a status attend
	 * @param id user
	 * @param book
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */

	@Override
	public boolean remove_from_queue_book(int id_user, int id_book) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * On va supprimer par la queue la requeste de l'user s'il a status attend
	 * @param id meta
	 * @param book
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */

	@Override
	public boolean remove_from_queue_meta_resource(int id_user, int id_meta) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/*
	 * Il retourne tous les resources qui l'user a ajouté
	 * @param id user
	 * @param flag qui nous dit si la resource est un meta ou book
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */
	
	@Override
	public UpemResponseImp show_resource_add_by_user(int id_user, boolean meta) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * Il retourne tous les livres qui l'user a ajouté
	 * @param id user
	 * @param flag qui nous dit si la resource est un meta ou book
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */

	@Override
	public UpemResponseImp show_book_add_by_user(int id_user, boolean meta) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * Il retourne tous les meta_resource qui l'user a ajouté
	 * @param id user
	 * @param flag qui nous dit si la resource est un meta ou book
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 */

	@Override
	public UpemResponseImp show_meta_resource_add_by_user(int id_user, boolean meta) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
    
}
