package microservicewithproxy.integration;

import microservicewithproxy.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
@TestPropertySource(locations="classpath:application-test.properties")
public class BookMicroserviceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createBook() throws Exception{

        //TODO - Add script for DB initialization
        /*
        Book createBook = new Book(null, "12340", "Author", "Name", 555, "Description");

        ResponseEntity<Book> responseEntity =
                restTemplate.postForEntity("/books", createBook, Book.class);
        Book createdBook = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(createdBook.getId());
        */

    }
}
