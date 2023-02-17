package com.staffmate.StaffMateDocumentManagement.errors;

public class ParsingPDFError extends Exception {

    public ParsingPDFError(String pdfName) {
        super("Error while parsing PDF with name: " + pdfName);
    }
}
