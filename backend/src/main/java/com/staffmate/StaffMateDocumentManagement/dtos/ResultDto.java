package com.staffmate.StaffMateDocumentManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    public String firstname;
    public String lastname;
    public String education;
    public String address;
    public String phone;
    public String email;
    public String cvContent;
    public String letterContent;
    public String cvFilename;
    public String letterFilename;
}
