package com.staffmate.StaffMateDocumentManagement.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "staffmate-letter")
@Data
@NoArgsConstructor
public class CoverLetter {
    @Id
    private String id;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Applicant applicant;

    @Field(type = FieldType.Text, store = true)
    private String content;

    public CoverLetter(Applicant applicant, String content) {
        this.applicant = applicant;
        this.content = content;
    }
}