package microservicewithproxy.model;

public class Converter {

    public static microservicewithproxy.model.external.Book convertToExternal(microservicewithproxy.model.jpa.Book book){
        microservicewithproxy.model.external.Book extBook = new microservicewithproxy.model.external.Book();
        extBook.setAuthor(book.getAuthor());
        extBook.setDescription(book.getDescription());
        extBook.setIsbn(book.getIsbn());
        extBook.setName(book.getName());
        extBook.setPages(book.getPages());
        extBook.setId(book.getId());
        return extBook;
    }

    public static microservicewithproxy.model.jpa.Book convertToJpa(microservicewithproxy.model.external.Book book){
        microservicewithproxy.model.jpa.Book jpaBook = new microservicewithproxy.model.jpa.Book();
        jpaBook.setAuthor(book.getAuthor());
        jpaBook.setDescription(book.getDescription());
        jpaBook.setIsbn(book.getIsbn());
        jpaBook.setName(book.getName());
        jpaBook.setPages(book.getPages());
        jpaBook.setId(book.getId());
        return jpaBook;
    }
}
