package com.staffmate.StaffMateDocumentManagement.services;

import com.staffmate.StaffMateDocumentManagement.content_extractors.ContentExtractor;
import com.staffmate.StaffMateDocumentManagement.dtos.NewApplication;
import com.staffmate.StaffMateDocumentManagement.models.Applicant;
import com.staffmate.StaffMateDocumentManagement.models.Application;
import com.staffmate.StaffMateDocumentManagement.models.CV;
import com.staffmate.StaffMateDocumentManagement.models.CoverLetter;
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
        Applicant applicant = modelMapper.map(newApplication, Applicant.class);
        try {
            String cvContent = contentExtractor.extractContent(newApplication.getCv());
            CV cv = new CV(cvContent);

            String letterContent = contentExtractor.extractContent(newApplication.getLetter());
            CoverLetter coverLetter = new CoverLetter(letterContent);

            Application application = new Application(applicant, cv, coverLetter);
            applicationRepository.save(application);

            storageService.save(application.getApplicant().getFirstname(), application.getApplicant().getLastname(), newApplication.getCv());
            storageService.save(application.getApplicant().getFirstname(), application.getApplicant().getLastname(), newApplication.getLetter());
        } catch (Exception ignored) {

        }

    }

    public Resource get(String fileName) {
        return storageService.load("fileName");
    }
}
