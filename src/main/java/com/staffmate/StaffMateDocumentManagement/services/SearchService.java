package com.staffmate.StaffMateDocumentManagement.services;

import com.staffmate.StaffMateDocumentManagement.dtos.ComplexSearchDto;
import com.staffmate.StaffMateDocumentManagement.dtos.SearchFiledDto;
import com.staffmate.StaffMateDocumentManagement.models.Application;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public List<Application> search(ComplexSearchDto complexSearchDto) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        QueryBuilder matchQuery = null;
        for (SearchFiledDto filed : complexSearchDto.getFields()) {
            matchQuery = selectQueryBuilder(filed);
            if (filed.getIsMustContain()) {
                queryBuilder.must(matchQuery);
            } else if (filed.getIsMustNotContain()) {
                queryBuilder.mustNot(matchQuery);
            } else {
                queryBuilder.should(matchQuery);
            }
        }
        return getApplications(queryBuilder, getHighlightBuilder(complexSearchDto));
    }

    private HighlightBuilder getHighlightBuilder(ComplexSearchDto complexSearchDto) {
        HighlightBuilder builder = new HighlightBuilder();
        complexSearchDto.getFields().stream().map(filed -> builder.field(filed.getFieldName()));
        return builder;
    }

    private QueryBuilder simpleSearch(SearchFiledDto searchFiledDto) {

        return QueryBuilders
                .multiMatchQuery(searchFiledDto.getQuery().toLowerCase(), searchFiledDto.getFieldName())
                .fuzziness(Fuzziness.AUTO);
    }

    private QueryBuilder selectQueryBuilder(SearchFiledDto field){
        if(field.getQuery().startsWith("\"") && field.getQuery().endsWith("\"")){
            return phraseSearch(field);
        }else{
            return simpleSearch(field);
        }
    }
    private QueryBuilder phraseSearch(SearchFiledDto searchFiledDto) {
        return QueryBuilders.matchPhraseQuery(searchFiledDto.getQuery().toLowerCase(), searchFiledDto.getFieldName());
    }

    private List<Application> getApplications(QueryBuilder queryBuilder, HighlightBuilder highlightBuilder) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withHighlightBuilder(highlightBuilder)
                .withPageable(PageRequest.of(0, 10))
                .build();

        return elasticsearchOperations.search(searchQuery,
                        Application.class,
                        IndexCoordinates.of("staffmate-application"))
                .stream()
                .map(SearchHit<Application>::getContent)
                .collect(Collectors.toList());
    }
}
