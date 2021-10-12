package com.jp.library.component;

import com.jp.library.dto.BookTemplate;
import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CSVParser implements Parser {

    @Override
    public List<BookTemplate> parse(String path) {

        List<BookTemplate> bookTemplateList = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(path));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == 4) {
                    BookTemplate bookTemplate = new BookTemplate();
                    if (!StringUtils.isAnyBlank(nextLine)) {
                        bookTemplate.setIsbn(nextLine[0]);
                        bookTemplate.setName(nextLine[1]);
                        bookTemplate.setAuthor(nextLine[2]);

                        List<String> tagList = Arrays.asList(nextLine[3].trim().split(","));
                        bookTemplate.setTags(tagList);
                        bookTemplateList.add(bookTemplate);

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookTemplateList;
    }

}
