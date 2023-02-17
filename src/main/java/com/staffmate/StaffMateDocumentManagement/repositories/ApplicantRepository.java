package com.staffmate.StaffMateDocumentManagement.repositories;

import com.staffmate.StaffMateDocumentManagement.models.Applicant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends ElasticsearchRepository<Applicant, String> {
}
