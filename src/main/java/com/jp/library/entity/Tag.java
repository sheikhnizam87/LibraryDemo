package com.jp.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    List<Book> books = new ArrayList<>();

    public Tag(String tagName) {
        this.name = tagName;
    }


    public void addBooks(Book taggedBooks) {
        this.books.add(taggedBooks);
    }
}
