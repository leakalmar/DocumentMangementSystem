package com.staffmate.StaffMateDocumentManagement.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.elasticsearch.common.geo.GeoPoint;

import java.sql.Timestamp;
import java.util.Date;

@Document(indexName = "staffmate-applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    private String id;
    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String name;

    @Field(type = FieldType.Text, searchAnalyzer = "serbian", analyzer = "serbian", store = true)
    private String surname;

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
    private String coverLetterContent;

    @Field(type = FieldType.Text, store = true)
    private String coverLetterFilename;

    @Field(type = FieldType.Date, store = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private Date applicationTimestamp;


}
