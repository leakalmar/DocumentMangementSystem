package com.staffmate.StaffMateDocumentManagement.content_extractors;

import com.staffmate.StaffMateDocumentManagement.errors.ParsingPDFError;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ContentExtractorPDF implements ContentExtractor {
    public String extractContent(final MultipartFile multipartFile) throws ParsingPDFError {
        String text = null;

        try (final PDDocument document = PDDocument.load(multipartFile.getInputStream())) {
            final PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (final Exception ex) {
            throw new ParsingPDFError(multipartFile.getName());
        }

        return text;
    }
}

