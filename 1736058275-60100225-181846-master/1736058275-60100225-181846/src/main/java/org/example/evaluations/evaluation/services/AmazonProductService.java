package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.AmazonProductDataDto;
import org.example.evaluations.evaluation.dtos.AmazonProductDto;
import org.example.evaluations.evaluation.models.AmazonProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AmazonProductService implements IProductService {

    private final String rapidApiKeyName = "x-rapidapi-key";
    private final String rapidApiKeyValue = "fb4f1ffe57msh514aca2f75d9aa2p149ed6jsn2cd8cee2d239";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public List<AmazonProduct> getProductByName(String name) {

        String url = "https://real-time-amazon-data.p.rapidapi.com/search?query={name}";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(rapidApiKeyName, rapidApiKeyValue);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = this.restTemplateBuilder.build();

        AmazonProductDto result = restTemplate.exchange(url, HttpMethod.GET, entity, AmazonProductDto.class, name).getBody();

        List<AmazonProduct> products = result.getData().getProducts();

        return products;
    }

    public List<AmazonProduct> getProductByCategoryId(String categoryId) {

        String url = "https://real-time-amazon-data.p.rapidapi.com/products-by-category?category_id={cid}";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(rapidApiKeyName, rapidApiKeyValue);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = this.restTemplateBuilder.build();

        AmazonProductDto result = restTemplate.exchange(url, HttpMethod.GET, entity, AmazonProductDto.class, categoryId).getBody();

        List<AmazonProduct> products = result.getData().getProducts();

        return products;

    }
}