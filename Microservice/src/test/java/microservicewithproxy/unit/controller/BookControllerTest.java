package microservicewithproxy.unit.controller;


import microservicewithproxy.TestUtil;
import microservicewithproxy.controller.BookController;
import microservicewithproxy.model.external.Book;
import microservicewithproxy.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookServiceMock;

    @Test
    public void createBookSuccessfully() throws Exception{

        Book createdBook = new Book(null, "12340", "Author", "Name", 555, "Description");
        given(bookServiceMock.create(createdBook)).willReturn(createdBook);

        this.mockMvc.perform(
                post("/books")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(TestUtil.asJsonString(createdBook)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createBookMissingIsbn() throws Exception{
        Book createdBook = new Book(null, "", "Author", "Name", 555, "Description");

        this.mockMvc.perform(
                post("/books")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(TestUtil.asJsonString(createdBook)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createBookMissingAuthor() throws Exception{
        Book createdBook = new Book(null, "1234", "", "Name", 555, "Description");

        this.mockMvc.perform(
                post("/books")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(TestUtil.asJsonString(createdBook)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createBookMissingName() throws Exception{
        Book createdBook = new Book(null, "1234", "Author", "", 555, "Description");

        this.mockMvc.perform(
                post("/books")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(TestUtil.asJsonString(createdBook)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createBookMissingPages() throws Exception{
        Book createdBook = new Book(null, "1234", "Author", "Name ", null, "Description");

        this.mockMvc.perform(
                post("/books")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(TestUtil.asJsonString(createdBook)))
                .andExpect(status().isBadRequest());
    }


}
