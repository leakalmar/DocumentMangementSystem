package com.staffmate.StaffMateDocumentManagement.controllers;

import com.staffmate.StaffMateDocumentManagement.dtos.NewApplication;
import com.staffmate.StaffMateDocumentManagement.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/upload")
@CrossOrigin
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "/")
    public ResponseEntity<?> uploadApplication(@ModelAttribute NewApplication newApplication) {
        uploadService.upload(newApplication);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
