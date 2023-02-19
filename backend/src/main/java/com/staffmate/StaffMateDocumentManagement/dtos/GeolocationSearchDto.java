package com.staffmate.StaffMateDocumentManagement.dtos;

import lombok.Data;

@Data
public class GeolocationSearchDto {

    private String city;
    private int radius;
}
