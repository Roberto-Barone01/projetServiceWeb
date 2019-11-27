
package upem.server.connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import upem.shared.interfaces.BookAddable;


public class Book extends UnicastRemoteObject implements BookAddable {
    
    String title;
    String publisher;
    int pages;
    String isbn;
    String comment;
    String state;
    double price;
    String edition;
    String year;
    
    public BookAddable edition(String edition){
        this.edition = edition;
        return this;
    }
    
    public BookAddable year(String year){
        this.year = year;
        return this;
    }
    

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getComment() {
        return comment;
    }

    public String getState() {
        return state;
    }

    public double getPrice() {
        return price;
    }
    
    
    Book() throws RemoteException{
        super();
    }

    public BookAddable title(String title) throws RemoteException {
        this.title = title;
        return this;
    }

    public BookAddable publisher(String publisher) throws RemoteException {
        this.publisher = publisher;
        return this;
    }


    public BookAddable pages(int pages) throws RemoteException {
        this.pages = pages;
        return this;
    }

    public BookAddable isbn(String isbn) throws RemoteException {
        this.isbn = isbn;
        return this;
    }

    public BookAddable comment(String comment) throws RemoteException {
        this.comment = comment;
        return this;
    }

    public BookAddable state(String state) throws RemoteException {
        this.state = state;
        return this;
    }

    public BookAddable price(double price) throws RemoteException {
        this.price = price;
        return this;
    }

    public String toString(){
        return this.title;
    }

    public String getEdition() throws RemoteException {
        return this.edition;
    }

    public String getYear() throws RemoteException {
        return this.year;
    }

 
}
