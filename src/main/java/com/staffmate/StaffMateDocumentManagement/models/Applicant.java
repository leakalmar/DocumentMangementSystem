package com.staffmate.StaffMateDocumentManagement.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "staffmate-applicant")
@Data
@NoArgsConstructor
public class Applicant {

    @Id
    private String id;

    @Field(type = FieldType.Text, store = true)
    private String firstname;

    @Field(type = FieldType.Text, store = true)
    private String lastname;

    @Field(type = FieldType.Text, index = false, store = true)
    private String email;

    @Field(type = FieldType.Text, index = false, store = true)
    private String address;

    @Field(type = FieldType.Text, index = false, store = true)
    private String phone;

    @Field(type = FieldType.Text, store = true)
    private String education;

    @GeoPointField
    private GeoPoint location;
}
