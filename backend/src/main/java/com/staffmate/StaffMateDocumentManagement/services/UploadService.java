package com.staffmate.StaffMateDocumentManagement.services;

import com.staffmate.StaffMateDocumentManagement.content_extractors.ContentExtractor;
import com.staffmate.StaffMateDocumentManagement.dtos.NewApplication;
import com.staffmate.StaffMateDocumentManagement.models.Application;
import com.staffmate.StaffMateDocumentManagement.repositories.ApplicationRepository;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class UploadService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ContentExtractor contentExtractor;

    @Autowired
    private GeolocationService geolocationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    FileStorageService storageService;

    public void upload(NewApplication newApplication) throws IOException {
        Application application = modelMapper.map(newApplication, Application.class);

        application.setLocation(geolocationService.getCoordinates(application.getAddress()));

        try {
            String cvContent = contentExtractor.extractContent(newApplication.getCv());
            String coverLetterContent = contentExtractor.extractContent(newApplication.getLetter());


            application.setCvContent(cvContent);
            application.setCoverLetterContent(coverLetterContent);

            String cvFilename = storageService.save(newApplication.getCv());
            String letterFilename = storageService.save(newApplication.getLetter());
            application.setCvFilename(cvFilename);
            application.setCoverLetterFilename(letterFilename);
            application.setApplicationTimestamp(new Date());
            applicationRepository.save(application);
        } catch (Exception ignored) {

        }

    }

    public Resource get(String fileName) {
        return storageService.load(fileName);
    }
}
