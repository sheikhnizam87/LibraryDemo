package com.jp.library.service;

import com.jp.library.LibraryApplication;
import com.jp.library.dto.ResponseObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= LibraryApplication.class)
public class SearchServiceTest {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SearchService searchService;

    @Test
    @Transactional
    public void testSearchBookByBookName() {
        List<ResponseObject> response = searchService.getBookByName("The Little Engine That Could");
        assertTrue(response.size()>0);
    }

    @Test
    @Transactional
    public void testSearchBookByBookAuthor() {
        List<ResponseObject> response = searchService.getBookByAuthorName("Antoine de Saint");
        assertTrue(response.size()>0);
    }

    @Test
    @Transactional
    public void testSearchBookByTag() {
        List<ResponseObject> response = searchService.getBookByTagName("Horror");
        assertTrue(response.size()==2);
        assertTrue(response.get(0).getName().equals("The Little Engine That Could") || response.get(0).getName().equals("Stuart Little"));
    }

    @Test
    @Transactional
    public void testSearchBookByBookName_invalid() {
        List<ResponseObject> response = searchService.getBookByName("The Little Engine");
        assertTrue(response.size() ==0);
    }

    @Test
    @Transactional
    public void testSearchBookByBookAuthor_invalid() {
        List<ResponseObject> response = searchService.getBookByAuthorName("Blade wilson");
        assertTrue(response.size() ==0);
    }

    @Test
    @Transactional
    public void testSearchBookByTag_invalid() {
        List<ResponseObject> response = searchService.getBookByAuthorName("Comic");
        assertTrue(response.size() ==0);
    }
}
