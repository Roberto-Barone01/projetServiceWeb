package upem.server.connection;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import upem.shared.interfaces.BookProperty;
import upem.shared.interfaces.MetaProperty;

public interface QueryRequestable {

	Connection connection() throws SQLException;

	void stop(Connection conn) throws SQLException;

	boolean test();

	int id_user(String user) throws SQLException;

	String password(int id) throws SQLException;

	void addBook(BookProperty book, int id_user) throws SQLException, RemoteException;

	UpemResponseImp resources(boolean meta) throws SQLException;

	UpemResponseImp meta() throws SQLException;

	UpemResponseImp books() throws SQLException;

	UnaryUpemResponseImp info_resource(boolean meta, int id) throws SQLException;

	UnaryUpemResponseImp infoBook(int id) throws SQLException;

	UnaryUpemResponseImp infoMeta(int id) throws SQLException;

	boolean resource_served(int id, boolean meta) throws SQLException;

	boolean book_served(int id) throws SQLException;

	boolean meta_served(int id) throws SQLException;

	boolean reserve_resource_to_user(int id_resource, boolean meta, int id_user) throws SQLException;

	boolean reserve_book_to_user(int id_book, int id_user) throws SQLException;

	boolean reserve_meta_to_user(int id_meta, int id_user) throws SQLException;

	boolean add_user_to_queue(int id_resource, boolean meta, int id_user) throws SQLException;

	boolean add_user_to_queue_book(int id_book, int id_user) throws SQLException;

	boolean add_user_to_queue_meta(int id_meta, int id_user) throws SQLException;

	void addMeta(MetaProperty meta, int id_user) throws SQLException, RemoteException;

	void remove_resource(int id, boolean meta) throws SQLException;

	void remove_book(int id) throws SQLException;

	void remove_meta_resource(int id) throws SQLException;

	void remove_from_queue(int id_user, int id_resource, boolean meta) throws SQLException;

	void remove_from_queue_book(int id_user, int id_book) throws SQLException;

	void remove_from_queue_meta_resource(int id_user, int id_meta) throws SQLException;

	UpemResponseImp show_resource_add_by_user(int id_user, boolean meta) throws SQLException;

	UpemResponseImp show_book_add_by_user(int id_user) throws SQLException;

	UpemResponseImp show_meta_resource_add_by_user(int id_user) throws SQLException;
	
	UpemResponseImp show_queue_of_resource(boolean meta, int id_resource) throws SQLException;
	
	UpemResponseImp show_queue_of_book(int id_book) throws SQLException;

	UpemResponseImp show_queue_of_meta(int id_meta) throws SQLException;
	
	// Se il libro è richiesto da altri deve anche assegnarlo ad altri //
	void add_complete_status_request(int id_user,int id_resource, boolean meta) throws SQLException;
	
	UpemResponseImp show_all_queue_user(int id_user, boolean meta) throws SQLException;
	

	int candidateForResource(int id_resource, boolean meta) throws SQLException;
	
	/* CLIENTS OPERATION */
	
	String password_client(String user) throws SQLException;
	
	String client_book() throws SQLException;
	
	boolean client_can_purchase_this(int id_book) throws SQLException;
	
	void add_to_basket(int id_book, int id_client) throws SQLException;

	void achete_book(int id_book) throws SQLException;
	
	String id_client(String user) throws SQLException;
	
	ArrayList<Integer> basket_user (int id_client) throws SQLException;

	UpemResponseImp showResourceLent(int idUser, boolean b) throws SQLException;


}