package microservicewithproxy.service;

import microservicewithproxy.exception.DuplicateDataException;
import microservicewithproxy.exception.NotFoundException;
import microservicewithproxy.exception.ServerErrorException;
import microservicewithproxy.model.Converter;
import microservicewithproxy.model.external.Book;
import microservicewithproxy.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> getAll() {
        List<microservicewithproxy.model.jpa.Book> books = (List<microservicewithproxy.model.jpa.Book>) bookRepository.findAll();

        List<Book> extBookList = new ArrayList<Book>();
        books.forEach(book -> extBookList.add(Converter.convertToExternal(book)));

        return extBookList;
    }

    @Override
    public Book getBook(Long id) {

        microservicewithproxy.model.jpa.Book book = bookRepository.findOne(id);
        if ((book == null)) {
            throw new NotFoundException(String.valueOf(id));
        }

        return Converter.convertToExternal(book);
    }

    @Override
    public Book create(Book book) {

        microservicewithproxy.model.jpa.Book jpaBook = Converter.convertToJpa(book);
        microservicewithproxy.model.jpa.Book createdJpaBook = bookRepository.save(jpaBook);
        Book createdExternalBook = Converter.convertToExternal(createdJpaBook);

        return createdExternalBook;
    }

    @Override
    public Book update(Book book) {
        microservicewithproxy.model.jpa.Book updatedJpaBook = new microservicewithproxy.model.jpa.Book();

        microservicewithproxy.model.jpa.Book jpaBook = Converter.convertToJpa(book);
        try {
            updatedJpaBook = bookRepository.save(jpaBook);
        }catch(DataIntegrityViolationException e){
            throw new DuplicateDataException("Duplicating unique information found in the request is not allowed");
        }
        Book updatedExternalBook = Converter.convertToExternal(updatedJpaBook);

        return updatedExternalBook;
    }

    @Override
    public List<Book> findBooks(String isbn) {

        List<microservicewithproxy.model.jpa.Book> books;
        if(isbn == null || isbn.isEmpty()){
            books = (List<microservicewithproxy.model.jpa.Book>) bookRepository.findAll();
        }else {
            books = bookRepository.findByIsbn(isbn);
        }

        List<Book> extBookList = new ArrayList<Book>();
        books.forEach(book -> extBookList.add(Converter.convertToExternal(book)));

        return extBookList;
    }

    @Override
    public void delete(Long id) {
        try {
            bookRepository.delete(id);
        }catch(EmptyResultDataAccessException e){
            throw new NotFoundException(String.valueOf(id));
        }catch (Exception e){
            throw new ServerErrorException();
        }
    }


}
