package upem.server.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface QueryRequestable {

	Connection connection() throws SQLException;

	void stop(Connection conn) throws SQLException;

	boolean test();

	int id_user(String user);

	String password(int id) throws SQLException;

	void addBook(Book book, int id_user) throws SQLException;

	/*
	 * Il retourne toue les données relative à la table meta_resource ou book
	 * @param flag qui dit si on veut la table book ou meta_resource (TRUE = meta_resource, FALSE = book)
	 * @throws RemoteException
	 * @throws SQLExcepion s'il y a des erreur au moment qu'on nous va connecter avec le db
	 * @return UpemResponse avec tout les donnees dans la table book ou meta_resource
	 */
	UpemResponseImp resources(boolean meta) throws SQLException;

	UpemResponseImp meta() throws SQLException;

	UpemResponseImp books() throws SQLException;

	/*
	Il retourne tous les info d'un produit en donnent l'id
	@param boolean meta, qui nous dit s'il faut chercher dans la table meta resource ou book (TRUE = meta_resource, FALSE = book)
	@param id de la resource qu'on va chercher dans la table meta_resource ou book
	@return UnaryUpemResponse avec les données qui consiste de tous les info dans la table book ou meta et les info sur l'user qui a ajouté la resource
	et la date.
	*/
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

	void addMeta(Meta meta, int id_user) throws SQLException;

	boolean remove_resource(int id, boolean meta) throws SQLException;

	boolean remove_book(int id) throws SQLException;

	boolean remove_meta_resource(int id) throws SQLException;

	boolean remove_from_queue(int id_user, int id_resource, boolean meta) throws SQLException;

	boolean remove_from_queue_book(int id_user, int id_book) throws SQLException;

	boolean remove_from_queue_meta_resource(int id_user, int id_meta) throws SQLException;

	UpemResponseImp show_resource_add_by_user(int id_user, boolean meta) throws SQLException;

	UpemResponseImp show_book_add_by_user(int id_user, boolean meta) throws SQLException;

	UpemResponseImp show_meta_resource_add_by_user(int id_user, boolean meta) throws SQLException;

}