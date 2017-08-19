package microservicewithproxy.controller;

import microservicewithproxy.model.external.Book;
import microservicewithproxy.model.external.BookValidator;
import microservicewithproxy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@Transactional(readOnly = true)
public class BookController {

    @Autowired
    private BookService bookService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new BookValidator());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Book> getBook(@PathVariable("id") Long id) {

        return ResponseEntity.ok(bookService.getBook(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> searchByIsbn(@RequestParam(value = "isbn", required = false) String isbn) {

        List<Book> books = bookService.findBooks(isbn);

        Resources<Book> resources = new Resources<Book>(books);

        return ResponseEntity.ok(resources);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<Book> create(@Valid @RequestBody Book book){

        Book createdBook = bookService.create(book);

        return new ResponseEntity<Book>(createdBook, HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<Book> update(@Valid @RequestBody Book book){

        Book updatedBook =  bookService.update(book);

        return ResponseEntity.ok(updatedBook);
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("id") Long id){

        bookService.delete(id);

        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }


}
