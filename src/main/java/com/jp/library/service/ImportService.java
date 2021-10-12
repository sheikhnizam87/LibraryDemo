package com.jp.library.service;

import com.jp.library.dao.BookRepository;
import com.jp.library.dao.TagRepository;
import com.jp.library.dto.BookTemplate;
import com.jp.library.component.Decoder;
import com.jp.library.component.Parser;
import com.jp.library.entity.Book;
import com.jp.library.entity.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImportService {

    private final Decoder decoder;

    private final Parser parser;

    private final BookRepository bookRepository;

    private final TagRepository tagRepository;


    public void processRequest(String encodedFile) {

        String path = decoder.decode(encodedFile);
        List<BookTemplate> bookTemplateList = parser.parse(path);
        importBookList(bookTemplateList);

    }

    public void importBookList(List<BookTemplate> bookTemplateList) {

        Map<String, BookTemplate> booksInGroups = bookTemplateList.stream()
                .collect(Collectors.toMap(BookTemplate::getIsbn, bookTemplate -> bookTemplate, (oldValue, newValue) -> oldValue));

        Map<String, Long> booksWithCopies = bookTemplateList.stream()
                .collect(Collectors.groupingBy(BookTemplate::getIsbn, (Collectors.counting())));


        booksWithCopies.entrySet().stream().map(entry -> bookRepository.findById(entry.getKey())
                .map(book -> {
                    int copies = book.getCopies();
                    copies = copies + entry.getValue().intValue();
                    book.setCopies(copies);
                    return book;
                }).orElseGet(() -> {
                    BookTemplate bookTemplate = booksInGroups.get(entry.getKey());
                    Book book = new Book(bookTemplate.getIsbn(), bookTemplate.getName(), bookTemplate.getAuthor(), booksWithCopies.get(entry.getKey()).intValue());
                    bookRepository.save(book);

                    List<Tag> tags = new ArrayList<>();
                    for (String tagName : bookTemplate.getTags()) {
                        Optional<List<Tag>> tagList = tagRepository.findTagByName(tagName);
                        Tag tag;
                        if (tagList.isPresent() && tagList.get().size() > 0) {
                            tag = tagList.get().get(0);
                        } else {
                            tag = new Tag(tagName);
                        }
                        tags.add(tag);
                    }
                    for (Tag tag : tags) {
                        book.addTag(tag);
                        tag.addBooks(book);
                    }
                    tagRepository.saveAll(tags);
                    return book;
                }))
                .forEach(bookRepository::save);
    }
}
