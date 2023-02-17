package com.staffmate.StaffMateDocumentManagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "staffmate-applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    private String id;
    @Field(type = FieldType.Nested, includeInParent = true)
    private Applicant applicant;

    @Field(type = FieldType.Nested, includeInParent = true)
    private CV cv;

    @Field(type = FieldType.Nested, includeInParent = true)
    private CoverLetter coverLetter;

    public Application(Applicant applicant, CV cv, CoverLetter coverLetter) {
        this.applicant = applicant;
        this.cv = cv;
        this.coverLetter = coverLetter;
    }
}
