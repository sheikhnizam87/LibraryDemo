package com.jp.library.service;

import com.jp.library.component.RequestValidator;
import com.jp.library.dao.BookRepository;
import com.jp.library.dao.TagRepository;
import com.jp.library.dto.ResponseObject;
import com.jp.library.entity.Book;
import com.jp.library.entity.Tag;
import com.jp.library.util.Utility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final BookRepository bookRepository;

    private final TagRepository tagRepository;

    private final RequestValidator requestValidator;

    public SearchService(BookRepository bookRepository, TagRepository tagRepository, RequestValidator requestValidator) {
        this.bookRepository = bookRepository;
        this.tagRepository = tagRepository;
        this.requestValidator = requestValidator;
    }

    /**
     * Method to search list of books by name
     *
     * @param bookName
     * @return
     */
    public List<ResponseObject> getBookByName(String bookName) {
        requestValidator.checkBookName(bookName);
        Optional<List<Book>> bookList = bookRepository.findBookByName(bookName.toLowerCase());
        if (bookList.isPresent() && bookList.get().size() > 0) {
            return Utility.createResponseObjectList(bookList.get());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Method to search list of books by Author
     *
     * @param authorName
     * @return
     */
    public List<ResponseObject> getBookByAuthorName(String authorName) {
        requestValidator.checkAuthorName(authorName);
        Optional<List<Book>> bookList = bookRepository.findByAuthor(authorName.toLowerCase());
        if (bookList.isPresent() && bookList.get().size() > 0) {
            return Utility.createResponseObjectList(bookList.get());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Method to search list of books by tag name
     *
     * @param tagName
     * @return
     */
    public List<ResponseObject> getBookByTagName(String tagName) {
        requestValidator.checkTagName(tagName);
        Optional<List<Tag>> tagList = tagRepository.findTagByName(tagName.toLowerCase());

        if (tagList.isPresent() && tagList.get().size() > 0) {
            List<Book> bookList = tagList.get().stream().flatMap(tag -> tag.getBooks().stream()).collect(Collectors.toList());
            return Utility.createResponseObjectList(bookList);
        } else {
            return new ArrayList<>();
        }
    }
}
