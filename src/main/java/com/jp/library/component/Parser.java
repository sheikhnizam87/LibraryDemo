package com.jp.library.component;

import com.jp.library.dto.BookTemplate;

import java.util.List;

public interface Parser {

    public List<BookTemplate> parse(String path);
}
