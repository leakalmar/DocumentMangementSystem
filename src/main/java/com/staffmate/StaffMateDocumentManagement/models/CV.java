package com.staffmate.StaffMateDocumentManagement.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
public class CV {

    @Field(type = FieldType.Text, analyzer = "serbian", store = true)
    private String content;

    public CV(String content) {
        this.content = content;
    }
}