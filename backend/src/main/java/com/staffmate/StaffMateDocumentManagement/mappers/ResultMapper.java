package com.staffmate.StaffMateDocumentManagement.mappers;

import com.staffmate.StaffMateDocumentManagement.dtos.ComplexSearchDto;
import com.staffmate.StaffMateDocumentManagement.dtos.ResultDto;
import com.staffmate.StaffMateDocumentManagement.dtos.SearchFiledDto;
import com.staffmate.StaffMateDocumentManagement.models.Application;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ResultDto map(SearchHit<Application> applicationSearchHit, ComplexSearchDto complexSearchDto){
        List<String> searchFieldNames = new ArrayList<>();
        if(complexSearchDto != null){
            searchFieldNames = complexSearchDto.getFields().stream().map(SearchFiledDto::getFieldName).collect(Collectors.toList());
        }

        Application application = applicationSearchHit.getContent();

        ResultDto resultDto = modelMapper.map(application, ResultDto.class);

        if(!searchFieldNames.contains("cvContent")){
            resultDto.setCvContent("");
        }

        if(!searchFieldNames.contains("coverLetterContent")){
            resultDto.setCoverLetterContent("");
        }

        for(Map.Entry<String, List<String>> entry : applicationSearchHit.getHighlightFields().entrySet()){
            resultDto = changeResultValue(entry,resultDto);
        }
        return resultDto;
    }

    private ResultDto changeResultValue(Map.Entry<String, List<String>> entry, ResultDto resultDto){
        switch (entry.getKey()){
            case "name": resultDto.setName(entry.getValue().get(0));break;
            case "surname": resultDto.setSurname(entry.getValue().get(0));break;
            case "education": resultDto.setEducation(entry.getValue().get(0));break;
            case "cvContent":
                if(entry.getValue().size() > 1){
                    resultDto.setCvContent("..." + entry.getValue().get(0) + "..." + entry.getValue().get(1) + "...");
                }else{
                    resultDto.setCvContent("..." + entry.getValue().get(0));
                }
                break;
            case "coverLetterContent":
                if(entry.getValue().size() > 1){
                    resultDto.setCoverLetterContent("..." + entry.getValue().get(0) + "..." + entry.getValue().get(1) + "...");
                }else{
                    resultDto.setCoverLetterContent("..." + entry.getValue().get(0) + "...");
                }
                break;
            default: break;
        }
        return resultDto;
    }
}
