package com.staffmate.StaffMateDocumentManagement.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@NoArgsConstructor
public class Applicant {

    @Field(type = FieldType.Text, analyzer = "serbian", store = true)
    private String firstname;

    @Field(type = FieldType.Text, analyzer = "serbian", store = true)
    private String lastname;

    @Field(type = FieldType.Text, analyzer = "serbian", index = false, store = true)
    private String email;

    @Field(type = FieldType.Text, analyzer = "serbian", index = false, store = true)
    private String address;

    @Field(type = FieldType.Text, analyzer = "serbian", index = false, store = true)
    private String phone;

    @Field(type = FieldType.Text, analyzer = "serbian", store = true)
    private String education;

    @GeoPointField
    private GeoPoint location;
}
