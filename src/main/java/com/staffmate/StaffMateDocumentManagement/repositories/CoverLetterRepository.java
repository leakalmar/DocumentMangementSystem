package com.staffmate.StaffMateDocumentManagement.repositories;

import com.staffmate.StaffMateDocumentManagement.models.CoverLetter;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverLetterRepository extends ElasticsearchRepository<CoverLetter, String> {
}
