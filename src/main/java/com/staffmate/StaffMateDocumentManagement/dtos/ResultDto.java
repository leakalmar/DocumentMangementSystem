package com.staffmate.StaffMateDocumentManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ResultDto {
    public String title;
    public String cvFilename;
    public String letterFilename;
    public Map<String, List<String>> highlight;
}
