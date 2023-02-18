package com.staffmate.StaffMateDocumentManagement.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ComplexSearchDto {
    private List<SearchFiledDto> fields;
}
