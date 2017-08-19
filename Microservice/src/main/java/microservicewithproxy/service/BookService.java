package microservicewithproxy.service;

import microservicewithproxy.model.external.Book;

import java.util.List;

public interface BookService {

    public List<Book> getAll();
    public Book getBook(Long id);
    public Book create(Book book);
    public Book update(Book book);
    public List<Book> findBooks(String isbn);
    public void delete(Long id);
}
