package com.jp.library;

import com.jp.library.component.CSVParser;
import com.jp.library.dto.BookTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CSVParserTest {

    private CSVParser csvParser;

    @BeforeEach
    public void setUp() {

        csvParser = new CSVParser();
    }

    @Test
    public void givenTheFileHadThreeValidRecords() throws Exception {

       List<BookTemplate> bookTemplateList = csvParser.parse("./src/main/resources/templates/books.csv");

       assertThat(bookTemplateList.size() == 3);

    }
}
