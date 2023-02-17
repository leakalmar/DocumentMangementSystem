package com.staffmate.StaffMateDocumentManagement.controllers;

import com.staffmate.StaffMateDocumentManagement.dtos.NewApplication;
import com.staffmate.StaffMateDocumentManagement.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class FileController {

    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "upload")
    public ResponseEntity<?> uploadApplication(@ModelAttribute NewApplication newApplication) {
        uploadService.upload(newApplication);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "download/{fileName}")
    public ResponseEntity<?> get(@PathVariable String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        return new ResponseEntity<>(uploadService.get(fileName),headers, HttpStatus.OK);
    }
}
