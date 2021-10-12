package com.jp.library.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookTemplate {

    private String isbn;

    private String name;

    private String author;

    private List<String> tags;
}
