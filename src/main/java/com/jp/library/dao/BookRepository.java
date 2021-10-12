package com.jp.library.dao;

import com.jp.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {

    public Optional<List<Book>> findBookByName(String name);

    public Optional<List<Book>> findByAuthor(String author);


}
