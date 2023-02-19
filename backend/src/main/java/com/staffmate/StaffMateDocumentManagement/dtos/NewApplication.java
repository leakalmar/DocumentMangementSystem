package com.staffmate.StaffMateDocumentManagement.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewApplication {
    private String name;

    private String surname;

    private String email;

    private String address;

    private String phone;

    private String education;

    private MultipartFile cv;

    private MultipartFile letter;
}
