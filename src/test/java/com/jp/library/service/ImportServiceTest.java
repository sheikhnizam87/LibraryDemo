package com.jp.library.service;

import com.jp.library.dao.BookRepository;
import com.jp.library.dto.BookTemplate;
import com.jp.library.entity.Book;
import com.jp.library.util.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ImportServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ImportService importService;

    @Autowired
    BookRepository bookRepository;

    List<BookTemplate> bookTemplates;

    @BeforeEach
    public void setUp() {

        bookTemplates = DataGenerator.generateSetOfFourBooks();
    }

    @Test
    @Transactional
    @DirtiesContext
    public void testImportBookList() {

        importService.importBookList(bookTemplates);

        bookTemplates.stream().forEach(bookTemplate -> {
            Optional<List<Book>> bookList = bookRepository.findBookByName(bookTemplate.getName());
            if (bookList.isPresent() && bookList.get().size() > 0) {
                assertEquals(bookTemplate.getName(), bookList.get().get(0).getName());
            } else {
                fail("Book not found in Library");
            }
        });

    }


    @Test
    @DirtiesContext
    @Transactional
    public void testImportingBookListTwice() {

        bookTemplates.addAll(DataGenerator.generateSetOfFourBooks());
        importService.importBookList(bookTemplates);

        bookTemplates.stream().forEach(bookTemplate -> {
            Optional<List<Book>> bookList = bookRepository.findBookByName(bookTemplate.getName());
            if (bookList.isPresent() && bookList.get().size() > 0) {
                assertEquals(bookTemplate.getName(), bookList.get().get(0).getName());
                if (bookList.get().get(0).getName().equals("First Goal")) {
                    assertEquals(4, bookList.get().get(0).getCopies());
                }
            } else {
                fail("Book not found in Library");
            }
        });
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testUploadingAfile(){
        String encodedFile ="{\"formData\":\"data:text/csv;base64,MzU0NSxEYSB2aW5jaSBjb2RlLERhbiBicm93biwiU3VzcGVuc2UsVGhyaWxsZXIiCjk4NzY1LE9yZGVyIG9mIHBoZW9uaXgsSksgUm93bGluZyxGYW50YXN5Cjc2MzQ2OSxUaGUgR3JlYXQgR2F0c2J5LEYuIEZpdHhHZXJhbGQsIk5vdmVsLEZpY3Rpb25hbCIK\"}";
////        String encodedFile ="{\"formData\":\"data:text/csv;base64,MzU0NSxEYSB2aW5jaSBjb2RlLERhbiBicm93biwic3VzcGVuc2UsVGhyaWxsZXIiCjk4NzY1LE9yZGVyIG9mIHBoZW9uaXgsSksgUm93bGluZyxGYW50YXN5Cjc2MzQ2OSwgV2FyIGFuZCBQZWFjZSxMZW8gVG9sc3RveSwiTm92ZWwsRmljdGlvbmFsIgo=\"}";
//        String encodedFile ="{\"formData\":\"data:text/csv;base64,MzU0NSxEYSB2aW5jaSBjb2RlLERhbiBicm93biwic3VzcGVuc2UsVGhyaWxsZXIiCjk4NzY1LE9yZGVyIG9mIHBoZW9uaXgsSksgUm93bGluZyxGYW50YXN5Cjc2MzQ2OSwgV2FyIGFuZCBQZWFjZSxMZW8gVG9sc3RveSwiTm92ZWwsRmljdGlvbmFsIgo=\"}"
        importService.processRequest(encodedFile);

        Optional<List<Book>> bookList1 = bookRepository.findBookByName("Da vinci code");
        if (bookList1.isPresent() && bookList1.get().size() > 0) {
            assertEquals("Da vinci code", bookList1.get().get(0).getName());
            assertEquals("Dan brown", bookList1.get().get(0).getAuthor());
        } else {
            fail("Book not found in Library");
        }

        Optional<List<Book>> bookList2 = bookRepository.findBookByName("The Great Gatsby");
        if (bookList2.isPresent() && bookList2.get().size() > 0) {
            assertEquals("The Great Gatsby", bookList2.get().get(0).getName());
            assertEquals("F. FitxGerald", bookList2.get().get(0).getAuthor());
        } else {
            fail("Book not found in Library");
        }

        Optional<List<Book>> bookList3 = bookRepository.findBookByName("Order of pheonix");
        if (bookList3.isPresent() && bookList3.get().size() > 0) {
            assertEquals("Order of pheonix", bookList3.get().get(0).getName());
            assertEquals("JK Rowling", bookList3.get().get(0).getAuthor());
        } else {
            fail("Book not found in Library");
        }




    }

}
