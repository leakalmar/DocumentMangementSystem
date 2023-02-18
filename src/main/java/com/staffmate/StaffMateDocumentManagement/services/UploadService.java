package com.staffmate.StaffMateDocumentManagement.services;

import com.staffmate.StaffMateDocumentManagement.content_extractors.ContentExtractor;
import com.staffmate.StaffMateDocumentManagement.dtos.NewApplication;
import com.staffmate.StaffMateDocumentManagement.models.Application;
import com.staffmate.StaffMateDocumentManagement.repositories.ApplicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ContentExtractor contentExtractor;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    FileStorageService storageService;

    public void upload(NewApplication newApplication) {
        Application application = modelMapper.map(newApplication, Application.class);
        try {
            String cvContent = contentExtractor.extractContent(newApplication.getCv());
            String letterContent = contentExtractor.extractContent(newApplication.getLetter());


            application.setCvContent(cvContent);
            application.setLetterContent(letterContent);

            String cvFilename = storageService.save(newApplication.getCv());
            String letterFilename = storageService.save(newApplication.getLetter());
            application.setCvFilename(cvFilename);
            application.setLetterFilename(letterFilename);
            applicationRepository.save(application);
        } catch (Exception ignored) {

        }

    }

    public Resource get(String fileName) {
        return storageService.load(fileName);
    }
}
