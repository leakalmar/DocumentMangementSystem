package com.staffmate.StaffMateDocumentManagement.controllers;

import com.staffmate.StaffMateDocumentManagement.dtos.ComplexSearchDto;
import com.staffmate.StaffMateDocumentManagement.dtos.SearchFiledDto;
import com.staffmate.StaffMateDocumentManagement.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping(value = "")
    public ResponseEntity<?> search(@RequestBody ComplexSearchDto complexSearchDto) {
        return new ResponseEntity<>(searchService.search(complexSearchDto), HttpStatus.OK);
    }
}
