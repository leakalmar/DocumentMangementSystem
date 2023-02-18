package com.staffmate.StaffMateDocumentManagement.controllers;

import com.staffmate.StaffMateDocumentManagement.dtos.NewApplication;
import com.staffmate.StaffMateDocumentManagement.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
    public ResponseEntity<?> get(@PathVariable String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
        String contentType = null;
        Resource resource = uploadService.get(fileName);
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
