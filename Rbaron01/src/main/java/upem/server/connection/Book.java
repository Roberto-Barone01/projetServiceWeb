
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
    

    
    Book() throws RemoteException{
        super();
    }

    @Override
    public BookProperty title(String title) throws RemoteException {
        this.title = title;
        return this;
    }

    @Override
    public BookProperty publisher(String publisher) throws RemoteException {
        this.publisher = publisher;
        return this;
    }


    @Override
    public BookProperty pages(int pages) throws RemoteException {
        this.pages = pages;
        return this;
    }

    @Override
    public BookProperty isbn(String isbn) throws RemoteException {
        this.isbn = isbn;
        return this;
    }

    @Override
    public BookProperty comment(String comment) throws RemoteException {
        this.comment = comment;
        return this;
    }
    
    @Override
    public BookProperty state(String state) throws RemoteException {
        this.state = state;
        return this;
    }

    @Override
    public BookProperty price(double price) throws RemoteException {
        this.price = price;
        return this;
    }
    
    @Override
    public BookProperty edition(String edition) throws RemoteException{
        this.edition = edition;
        return this;
    }
    
    public BookProperty year(String year){
        this.year = year;
        return this;
    }

    public String toString(){
        return this.title;
    }

    public String getEdition(){
        return this.edition;
    }

    public String getYear(){
        return this.year;
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
    
    


 
}
