package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.LinkedInSearchRequest;
import org.example.evaluations.evaluation.dtos.LinkedInSearchResult;
import org.example.evaluations.evaluation.interceptors.HeaderRequestInterceptor;
import org.example.evaluations.evaluation.models.LinkedInSearchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class LinkedInSearchService implements ISearchService {

    private String rapidApiKeyName = "x-rapidapi-key";
    private String rapidApiKeyValue = "fb4f1ffe57msh514aca2f75d9aa2p149ed6jsn2cd8cee2d239";
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    //Take help from - https://binarycoders.wordpress.com/2020/10/04/add-a-header-to-spring-resttemplate/#:~:text=add(%20new%20HeaderRequestInterceptor(%20%22X,setInterceptors(interceptors)%3B
    public List<LinkedInSearchItem> searchPeople(LinkedInSearchRequest linkedInSearchRequest) {

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor(rapidApiKeyName, rapidApiKeyValue));

        RestTemplate restTemplate = this.restTemplateBuilder.build();
        restTemplate.setInterceptors(interceptors);

        String url = "https://linkedin-data-api.p.rapidapi.com/search-people-by-url";

        LinkedInSearchResult result = restTemplate.postForEntity(url, linkedInSearchRequest, LinkedInSearchResult.class).getBody();

        List<LinkedInSearchItem> searchItems = result.getData().getItems();

        return searchItems;

    }
}
