package com.jp.library.service;

import com.jp.library.dto.BookTemplate;
import com.jp.library.dto.ResponseObject;

import static org.junit.jupiter.api.Assertions.*;

import com.jp.library.exception.BadRequestException;
import com.jp.library.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;


@SpringBootTest
public class BookServiceTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BookService bookService;

    @Test
    @Transactional
    public void testRetrieveBookAndTagsByBookId() {
        ResponseObject response = bookService.getBookById("576875");
        assertEquals("life of pi", response.getName());
        assertEquals(1, response.getTags().size());
        assertTrue(response.getTags().stream().anyMatch(tag -> tag.equals("suspense")));
    }

    @Test
    @Transactional
    public void testRetrieveBookAndTagsBy_invalidBookId() {
        assertThrows(NotFoundException.class, () -> bookService.getBookById("6789654"));
    }


    @Test
    @DirtiesContext
    @Transactional
    public void testAddBooksWithTags() {

        BookTemplate bookTemplate1 = new BookTemplate();
        bookTemplate1.setIsbn("2445343");
        bookTemplate1.setName("heidi");
        bookTemplate1.setAuthor("johanna spyri");
        bookTemplate1.setTags(Arrays.asList("poetry", "biography"));

        bookService.addBook(bookTemplate1);
        bookService.addBook(bookTemplate1);
        ResponseObject response = bookService.getBookById("2445343");
        assertEquals("heidi", response.getName());
        assertEquals(2, response.getTags().size());
        assertEquals(2, response.getCopies());

    }


    @Test
    @DirtiesContext
    @Transactional
    public void testAddBooks_WithoutName() {


        BookTemplate bookTemplate2 = new BookTemplate();
        bookTemplate2.setIsbn("324555");
        bookTemplate2.setName("");
        bookTemplate2.setAuthor("jon krakauer");
        bookTemplate2.setTags(Arrays.asList("mystery", "humor"));

        assertThrows(BadRequestException.class, () -> bookService.addBook(bookTemplate2));
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testDeleteBookById() {

        ResponseObject response = bookService.getBookById("7545454");
        assertNotNull(response.getIsbn());
        bookService.deleteBookById("7545454");
        assertThrows(NotFoundException.class, () -> bookService.getBookById("7545454"));
    }


    @Test
    @DirtiesContext
    @Transactional
    public void testDeleteBookByEmptyId() {
        assertThrows(BadRequestException.class, () -> bookService.deleteBookById(""));
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testDeleteBookByInvalidId() {
        assertThrows(Exception.class, () -> bookService.deleteBookById("12345"));
    }


    @Test
    @DirtiesContext
    @Transactional
    public void testUpdateBooksWithTags() {

        ResponseObject response = bookService.getBookById("243566");
        assertEquals(2, response.getTags().size());
        BookTemplate bookTemplate1 = new BookTemplate();
        bookTemplate1.setIsbn("243566");
        bookTemplate1.setName("stuart little");
        bookTemplate1.setAuthor("e.b. white");
        bookTemplate1.setTags(Arrays.asList("poetry", "biography", "mystery"));

        bookService.updateBook(bookTemplate1.getIsbn().toLowerCase(), bookTemplate1);
        response = bookService.getBookById("243566");
        assertEquals("stuart little", response.getName());
        assertEquals(3, response.getTags().size());

    }

}
