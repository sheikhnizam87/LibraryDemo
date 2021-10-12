package com.jp.library.util;

import com.jp.library.dto.BookTemplate;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<BookTemplate> generateSetOfFourBooks(){
        //Book1
        BookTemplate bookTemplate1 =  new BookTemplate();
        bookTemplate1.setIsbn("42763339");
        bookTemplate1.setName("First Goal");
        bookTemplate1.setAuthor("Simon Schiffman");

        List<String> tags =  new ArrayList<>();
        tags.add("Horror");
        tags.add("Thriller");
        tags.add("Fiction");
        bookTemplate1.setTags(tags);


        //Book2
        BookTemplate bookTemplate2 =  new BookTemplate();
        bookTemplate2.setIsbn("45676543");
        bookTemplate2.setName("Space Heart");

        bookTemplate2.setAuthor("James Barbara");

        List<String> tags2 =  new ArrayList<>();
        tags2.add("Fantasy");
        bookTemplate2.setTags(tags2);


        //Book3
        BookTemplate bookTemplate3 =  new BookTemplate();
        bookTemplate3.setIsbn("45556777");
        bookTemplate3.setName("Harry Potter");

        bookTemplate3.setAuthor("JK rowlings");

        List<String> tags3 =  new ArrayList<>();
        tags3.add("Magic");
        tags3.add("Fantasy");
        bookTemplate3.setTags(tags3);


        //Book4
        BookTemplate bookTemplate4 =  new BookTemplate();
        bookTemplate4.setIsbn("3445656");
        bookTemplate4.setName("No where to look for");
        bookTemplate4.setAuthor("Steve Price");

        List<String> tags4 =  new ArrayList<>();
        tags4.add("Fiction");
        tags4.add("Fantasy");
        tags4.add("Thriller");
        bookTemplate4.setTags(tags);

        //book5
        BookTemplate bookTemplate5 =  new BookTemplate();
        bookTemplate5.setIsbn("42763339");
        bookTemplate5.setName("First Goal");
        bookTemplate5.setAuthor("Simon Schiffman");

        List<String> tags5 =  new ArrayList<>();
        tags5.add("Horror");
        tags5.add("Thriller");
        tags5.add("Fiction");
        bookTemplate5.setTags(tags5);

        List<BookTemplate> bookTemplates =  new ArrayList<>();
        bookTemplates.add(bookTemplate1);
        bookTemplates.add(bookTemplate2);
        bookTemplates.add(bookTemplate3);
        bookTemplates.add(bookTemplate4);
        bookTemplates.add(bookTemplate5);

        return bookTemplates;
    }
}
