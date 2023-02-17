package com.staffmate.StaffMateDocumentManagement.repositories;

import com.staffmate.StaffMateDocumentManagement.models.Application;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends ElasticsearchRepository<Application, String> {


}
