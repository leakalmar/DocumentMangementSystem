package com.staffmate.StaffMateDocumentManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "staffmate-applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    private String id;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String firstname;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String lastname;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", index = false, store = true)
    private String email;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", index = false, store = true)
    private String address;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", index = false, store = true)
    private String phone;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String education;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String cvContent;

    @Field(type = FieldType.Text, store = true)
    private String cvFilename;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String letterContent;

    @Field(type = FieldType.Text, store = true)
    private String letterFilename;


}
