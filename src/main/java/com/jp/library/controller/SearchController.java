package com.jp.library.controller;

import com.jp.library.dto.ResponseObject;
import com.jp.library.service.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("/byName/{bookName}")
    @ApiOperation(value ="get book details by book name", notes ="provide name of a book to lookup list of  book in the library")
    public ResponseEntity<List<ResponseObject>> getBooksByName(@PathVariable("bookName") String bookName){
        return ResponseEntity.ok(searchService.getBookByName(bookName));
    }

    @GetMapping("/byAuthor/{authorName}")
    @ApiOperation(value ="get book details by author name", notes ="provide author name to lookup books authored by in the library")

    public ResponseEntity<List<ResponseObject>> getBooksByAuthorName(@PathVariable("authorName") String authorName){
        return ResponseEntity.ok(searchService.getBookByAuthorName(authorName));
    }

    @GetMapping("/byTag/{tag}")
    @ApiOperation(value ="get book details by isbn", notes ="provide tag to lookup list of books marked under specific tag in the library")
    public ResponseEntity<List<ResponseObject>> getBooksByTagName(@PathVariable("tag") String tagName){
        return ResponseEntity.ok(searchService.getBookByTagName(tagName));
    }
}
