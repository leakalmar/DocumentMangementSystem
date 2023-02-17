package com.staffmate.StaffMateDocumentManagement.services;

import com.staffmate.StaffMateDocumentManagement.content_extractors.ContentExtractor;
import com.staffmate.StaffMateDocumentManagement.dtos.NewApplication;
import com.staffmate.StaffMateDocumentManagement.models.Applicant;
import com.staffmate.StaffMateDocumentManagement.models.CV;
import com.staffmate.StaffMateDocumentManagement.models.CoverLetter;
import com.staffmate.StaffMateDocumentManagement.repositories.ApplicantRepository;
import com.staffmate.StaffMateDocumentManagement.repositories.CVRepository;
import com.staffmate.StaffMateDocumentManagement.repositories.CoverLetterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private CoverLetterRepository coverLetterRepository;

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private ContentExtractor contentExtractor;

    @Autowired
    private ModelMapper modelMapper;

    public void upload(NewApplication newApplication) {
        Applicant applicant = modelMapper.map(newApplication, Applicant.class);
        applicantRepository.save(applicant);
        try {
            String cvContent = contentExtractor.extractContent(newApplication.getCv());
            CV cv = new CV(applicant, cvContent);
            cvRepository.save(cv);

            String letterContent = contentExtractor.extractContent(newApplication.getLetter());
            CoverLetter coverLetter = new CoverLetter(applicant, letterContent);
            coverLetterRepository.save(coverLetter);
        } catch (Exception ignored) {

        }

    }
}
