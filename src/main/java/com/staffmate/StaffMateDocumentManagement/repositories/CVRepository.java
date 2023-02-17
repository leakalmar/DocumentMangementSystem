package com.staffmate.StaffMateDocumentManagement.repositories;

import com.staffmate.StaffMateDocumentManagement.models.CV;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CVRepository extends ElasticsearchRepository<CV, String> {
}
