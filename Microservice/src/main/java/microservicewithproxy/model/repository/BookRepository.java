package microservicewithproxy.model.repository;

import microservicewithproxy.model.jpa.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findByIsbn(String isbn);

}
