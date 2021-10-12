package com.jp.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    private String isbn;

    private String name;

    private String author;

    @ManyToMany
    @JoinTable(name = "book_tag",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    List<Tag> tags = new ArrayList<>();

    private int copies;

    public Book(String isbn, String name, String author, int copies) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.copies = copies;
    }

    public Book(String isbn, String name, String author) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }


}
