package com.jp.library.service;

import com.jp.library.component.RequestValidator;
import com.jp.library.dao.BookRepository;
import com.jp.library.dao.TagRepository;
import com.jp.library.dto.BookTemplate;
import com.jp.library.dto.ResponseObject;
import com.jp.library.entity.Book;
import com.jp.library.entity.Tag;
import com.jp.library.exception.NotFoundException;
import com.jp.library.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final BookRepository bookRepository;

    private final TagRepository tagRepository;

    private final RequestValidator requestValidator;

    public BookService(BookRepository bookRepository, TagRepository tagRepository, RequestValidator requestValidator) {
        this.bookRepository = bookRepository;
        this.tagRepository = tagRepository;
        this.requestValidator = requestValidator;
    }

    public void addBook(BookTemplate request) {
        requestValidator.validateRequest(request);
        Optional<Book> optionalBook = bookRepository.findById(request.getIsbn());
        int countCopies = 0;
        if (optionalBook.isPresent()) {
            countCopies = optionalBook.get().getCopies();
        }
        Book book = new Book(request.getIsbn(), request.getName(), request.getAuthor(), countCopies + 1);

        List<Tag> tags = extractTagsFromRequest(request);
        if (tags.size() > 0) {
            tagRepository.saveAll(tags);
            associateTagsWithBooks(book, tags);
        }
        bookRepository.save(book);
        log.info(book.getName() + " Book is saved successfully ");
    }


    public ResponseObject updateBook(BookTemplate request) {
        requestValidator.validateRequest(request);
        Optional<Book> optionalBook = bookRepository.findById(request.getIsbn());
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setName(request.getName());
            book.setAuthor(request.getAuthor());
            book.setTags(new ArrayList<>());

            List<Tag> tags = extractTagsFromRequest(request);
            if (tags.size() > 0) {
                tagRepository.saveAll(tags);
                associateTagsWithBooks(book, tags);
            }
            bookRepository.save(book);
            log.info(book.getName() + " book is updated successfully");
            return Utility.createResponseObject(book);

        } else {
            throw new NotFoundException("Book with given ISBN does not exist in Library");
        }
    }


    public ResponseObject getBookById(String bookId) {
        requestValidator.checkIsbn(bookId);
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return Utility.createResponseObject(book);
        } else {
            throw new NotFoundException("Book with given ISBN does not exist in Library");
        }
    }

    public void deleteBookById(String bookId) {
        requestValidator.checkIsbn(bookId);
        bookRepository.deleteById(bookId);
        log.info("Book with Isbn :" + bookId + " is successfully deleted");
    }


    public Book associateTagsWithBooks(Book book, List<Tag> tags) {
        for (Tag tag : tags) {
            book.addTag(tag);
            tag.addBooks(book);
        }
        return book;
    }

    private List<Tag> extractTagsFromRequest(BookTemplate request) {
        List<Tag> tags = new ArrayList<>();
        for (String tagName : request.getTags()) {
            Optional<List<Tag>> tagList = tagRepository.findTagByName(tagName);
            if (tagList.isPresent() && tagList.get().size() > 0) {
                tags.add(tagList.get().get(0));
            } else {
                tags.add(new Tag(tagName));
            }
        }
        return tags;
    }

}

