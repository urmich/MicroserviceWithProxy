package microservicewithproxy.model.external;


import javax.validation.constraints.NotNull;

public class Book {

    public Book(){}

    public Book(Long id, String isbn, String author, String name, Integer pages, String description){
        this.id = id;
        this.isbn = isbn;
        this.author = author;
        this.name = name;
        this.pages = pages;
        this.description = description;
    }

    private Long id;
    private String isbn;
    private String author;
    private String name;
    private Integer pages;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
