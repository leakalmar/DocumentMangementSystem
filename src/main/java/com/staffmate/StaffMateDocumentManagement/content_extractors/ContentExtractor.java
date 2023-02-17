package com.staffmate.StaffMateDocumentManagement.content_extractors;

import com.staffmate.StaffMateDocumentManagement.errors.ParsingPDFError;
import org.springframework.web.multipart.MultipartFile;


public interface ContentExtractor {
    String extractContent(final MultipartFile multipartFile) throws ParsingPDFError;
}
