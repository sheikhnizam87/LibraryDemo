package com.jp.library.dto;

import com.jp.library.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {

    private String isbn;

    private String name;

    private String author;

    private List<String> tags;

    private int copies;


}
