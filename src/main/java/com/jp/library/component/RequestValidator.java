package com.jp.library.component;

import com.jp.library.dto.BookTemplate;
import com.jp.library.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public void validateRequest(BookTemplate request) {
        checkIsbn(request.getIsbn());
        checkBookName(request.getName());
        checkAuthorName(request.getAuthor());
    }


    public void checkIsbn(String isbn) {
        if(StringUtils.isBlank(isbn)){
            throw new BadRequestException("ISBN can not be blank");
        }
    }

    public void checkBookName(String bookName) {
        if(StringUtils.isBlank(bookName)){
            throw new BadRequestException("Book name can not be blank");
        }
    }

    public void checkAuthorName(String authorName) {
        if(StringUtils.isBlank(authorName)){
            throw new BadRequestException("Author name can not be blank");
        }
    }

    public void checkTagName(String tagName) {
        if(StringUtils.isBlank(tagName)){
            throw new BadRequestException("Can not search with blankTag name.");
        }
    }
}
