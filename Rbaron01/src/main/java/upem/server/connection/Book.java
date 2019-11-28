
package upem.server.connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import upem.shared.interfaces.BookProperty;


public class Book extends UnicastRemoteObject implements BookProperty {
    
    String title;
    String publisher;
    int pages;
    String isbn;
    String comment;
    String state;
    double price;
    String edition;
    String year;
    
    public Book edition(String edition){
        this.edition = edition;
        return this;
    }
    
    public Book year(String year){
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

    public Book title(String title) throws RemoteException {
        this.title = title;
        return this;
    }

    public Book publisher(String publisher) throws RemoteException {
        this.publisher = publisher;
        return this;
    }


    public Book pages(int pages) throws RemoteException {
        this.pages = pages;
        return this;
    }

    public Book isbn(String isbn) throws RemoteException {
        this.isbn = isbn;
        return this;
    }

    public Book comment(String comment) throws RemoteException {
        this.comment = comment;
        return this;
    }

    public Book state(String state) throws RemoteException {
        this.state = state;
        return this;
    }

    public Book price(double price) throws RemoteException {
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