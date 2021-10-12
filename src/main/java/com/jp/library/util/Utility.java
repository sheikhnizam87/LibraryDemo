package com.jp.library.util;

import com.jp.library.dto.ResponseObject;
import com.jp.library.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class Utility {

    public static ResponseObject createResponseObject(Book book) {
        List<String> tagNameList = book.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList());
        ResponseObject response = new ResponseObject(book.getIsbn(), book.getName(), book.getAuthor(), tagNameList, book.getCopies());
        return response;
    }

    public static List<ResponseObject> createResponseObjectList(List<Book> books) {
        return books.stream().map(book -> createResponseObject(book)).collect(Collectors.toList());
    }
}
