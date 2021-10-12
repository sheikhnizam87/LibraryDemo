package com.jp.library.controller;

import com.jp.library.dto.BookTemplate;
import com.jp.library.dto.ResponseObject;
import com.jp.library.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService searchService) {
        this.bookService = searchService;
    }


    @PostMapping("/create")
    @ApiOperation(value ="Add new book in the library", notes ="provide book details to add it in the library, one at a time.")
    public ResponseEntity addBook(@RequestBody BookTemplate requestObject) {
        bookService.addBook(requestObject);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{isbn}")
    @ApiOperation(value ="update any existing book in the library", notes ="provide book details with isbn to update the details in the library")
    public ResponseEntity<ResponseObject> updateBook(@PathVariable(value = "isbn") String isbn, @RequestBody BookTemplate requestObject) {
        return ResponseEntity.ok(bookService.updateBook(isbn, requestObject));

    }

    @GetMapping("/get/{isbn}")
    @ApiOperation(value ="get book details by isbn", notes ="provide  isbn to lookup specific book in the library")
    public ResponseEntity<ResponseObject> getBook(@PathVariable("isbn") String isbn) {
        return ResponseEntity.ok(bookService.getBookById(isbn));

    }

    @DeleteMapping("/delete/{isbn}")
    @ApiOperation(value ="delete book by isbn", notes ="provide an isbn  you can delete the details of book available in the library")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn) {
        bookService.deleteBookById(isbn);
        return ResponseEntity.ok().build();
    }

}
