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
        List<ResponseObject> response = searchService.getBookByName("the little engine that could");
        assertTrue(response.size()>0);
    }

    @Test
    @Transactional
    public void testSearchBookByBookAuthor() {
        List<ResponseObject> response = searchService.getBookByAuthorName("antoine de saint");
        assertTrue(response.size()>0);
    }

    @Test
    @Transactional
    public void testSearchBookByTag() {
        List<ResponseObject> response = searchService.getBookByTagName("horror");
        assertTrue(response.size()==2);
        assertTrue(response.get(0).getName().equals("the little engine that could") || response.get(0).getName().equals("stuart little"));
    }

    @Test
    @Transactional
    public void testSearchBookByBookName_invalid() {
        List<ResponseObject> response = searchService.getBookByName("the little engine");
        assertTrue(response.size() ==0);
    }

    @Test
    @Transactional
    public void testSearchBookByBookAuthor_invalid() {
        List<ResponseObject> response = searchService.getBookByAuthorName("blade wilson");
        assertTrue(response.size() ==0);
    }

    @Test
    @Transactional
    public void testSearchBookByTag_invalid() {
        List<ResponseObject> response = searchService.getBookByAuthorName("comic");
        assertTrue(response.size() ==0);
    }
}
