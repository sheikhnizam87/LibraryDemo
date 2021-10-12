package com.jp.library.util;

import com.jp.library.dto.BookTemplate;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<BookTemplate> generateSetOfFourBooks() {
        //Book1
        BookTemplate bookTemplate1 = new BookTemplate();
        bookTemplate1.setIsbn("42763339");
        bookTemplate1.setName("first goal");
        bookTemplate1.setAuthor("simon schiffman");

        List<String> tags = new ArrayList<>();
        tags.add("horror");
        tags.add("thriller");
        tags.add("fiction");
        bookTemplate1.setTags(tags);


        //Book2
        BookTemplate bookTemplate2 = new BookTemplate();
        bookTemplate2.setIsbn("45676543");
        bookTemplate2.setName("space heart");

        bookTemplate2.setAuthor("james barbara");

        List<String> tags2 = new ArrayList<>();
        tags2.add("fantasy");
        bookTemplate2.setTags(tags2);


        //Book3
        BookTemplate bookTemplate3 = new BookTemplate();
        bookTemplate3.setIsbn("45556777");
        bookTemplate3.setName("harry potter");

        bookTemplate3.setAuthor("jk rowlings");

        List<String> tags3 = new ArrayList<>();
        tags3.add("magic");
        tags3.add("fantasy");
        bookTemplate3.setTags(tags3);


        //Book4
        BookTemplate bookTemplate4 = new BookTemplate();
        bookTemplate4.setIsbn("3445656");
        bookTemplate4.setName("no where to look for");
        bookTemplate4.setAuthor("steve price");

        List<String> tags4 = new ArrayList<>();
        tags4.add("fiction");
        tags4.add("fantasy");
        tags4.add("thriller");
        bookTemplate4.setTags(tags);

        //book5
        BookTemplate bookTemplate5 = new BookTemplate();
        bookTemplate5.setIsbn("42763339");
        bookTemplate5.setName("first goal");
        bookTemplate5.setAuthor("simon schiffman");

        List<String> tags5 = new ArrayList<>();
        tags5.add("horror");
        tags5.add("thriller");
        tags5.add("fiction");
        bookTemplate5.setTags(tags5);

        List<BookTemplate> bookTemplates = new ArrayList<>();
        bookTemplates.add(bookTemplate1);
        bookTemplates.add(bookTemplate2);
        bookTemplates.add(bookTemplate3);
        bookTemplates.add(bookTemplate4);
        bookTemplates.add(bookTemplate5);

        return bookTemplates;
    }
}
