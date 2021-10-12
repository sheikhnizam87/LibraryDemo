package com.jp.library.controller;

import com.jp.library.dto.RequestObject;
import com.jp.library.service.ImportService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/import")
public class ImportController {

    private final ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/books")
    @ApiOperation(value ="import books from a csv", notes ="Provide a list of books to be added in the library in bulk")

    public ResponseEntity importBooks(@RequestBody RequestObject requestObject) {
        if (requestObject.getEncodedFile().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        importService.processRequest(requestObject.getEncodedFile());
        return ResponseEntity.ok().build();
    }
}
