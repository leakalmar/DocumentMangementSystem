package com.staffmate.StaffMateDocumentManagement.dtos;

import lombok.Data;

@Data
public class SearchFiledDto {
    private String fieldName;
    private String query;
    private Boolean isMustContain = false;
    private Boolean isMustNotContain = false;
}
