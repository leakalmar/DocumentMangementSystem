package com.staffmate.StaffMateDocumentManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    public String name;
    public String surname;
    public String education;
    public String address;
    public String phone;
    public String email;
    public String cvContent;
    public String coverLetterContent;
    public String cvFilename;
    public String coerLetterFilename;
}
