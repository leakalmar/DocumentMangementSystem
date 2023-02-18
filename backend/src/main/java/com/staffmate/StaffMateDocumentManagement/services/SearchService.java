package com.staffmate.StaffMateDocumentManagement.services;

import com.staffmate.StaffMateDocumentManagement.dtos.ComplexSearchDto;
import com.staffmate.StaffMateDocumentManagement.dtos.ResultDto;
import com.staffmate.StaffMateDocumentManagement.dtos.SearchFiledDto;
import com.staffmate.StaffMateDocumentManagement.models.Application;
import com.staffmate.StaffMateDocumentManagement.repositories.ApplicationRepository;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private ApplicationRepository applicationRepository;

    public List<ResultDto> search(ComplexSearchDto complexSearchDto) {
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

    private QueryBuilder selectQueryBuilder(SearchFiledDto field){
        if(field.getQuery().startsWith("\"") && field.getQuery().endsWith("\"")){
            return phraseSearch(field);
        }else{
            return simpleSearch(field);
        }
    }

    private QueryBuilder simpleSearch(SearchFiledDto searchFiledDto) {

        return QueryBuilders
                .multiMatchQuery(searchFiledDto.getQuery().toLowerCase(), searchFiledDto.getFieldName())
                .fuzziness(Fuzziness.AUTO);
    }

    private QueryBuilder phraseSearch(SearchFiledDto searchFiledDto) {
        String query = searchFiledDto.getQuery().trim().substring(1,searchFiledDto.getQuery().trim().length() -1).toLowerCase();
        return QueryBuilders.matchPhraseQuery(searchFiledDto.getFieldName(), query).slop(1);
    }

    private HighlightBuilder getHighlightBuilder(ComplexSearchDto complexSearchDto) {
        HighlightBuilder builder = new HighlightBuilder()
                .highlighterType("plain")
                .preTags("<strong>")
                .postTags("</strong>");
        for(SearchFiledDto field : complexSearchDto.getFields()){
            builder.field(field.getFieldName());
        }
        return builder;
    }

    private List<ResultDto> getApplications(QueryBuilder queryBuilder, HighlightBuilder highlightBuilder) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("content"))
                .withPageable(PageRequest.of(0, 10))
                .build();


        return elasticsearchOperations.search(searchQuery,
                        Application.class,
                        IndexCoordinates.of("staffmate-applications"))
                .stream()
                .map(applicationSearchHit -> new ResultDto(applicationSearchHit.getContent().getFirstname() + " " + applicationSearchHit.getContent().getLastname(), applicationSearchHit.getContent().getCvFilename(), applicationSearchHit.getContent().getLetterFilename(), applicationSearchHit.getHighlightFields()))
                .collect(Collectors.toList());
    }
}
