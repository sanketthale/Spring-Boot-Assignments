package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.RealTimeNewsResult;
import org.example.evaluations.evaluation.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyNewsService implements ICurrencyService {

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public List<News> getCurrencyNews(String fromCurrency,String toCurrency) {

        RestTemplate restTemplate = this.restTemplateBuilder.build();

        String url = "https://real-time-finance-data.p.rapidapi.com/currency-news?from_symbol={currency1}&to_symbol={currency2}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", rapidApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<RealTimeNewsResult> response = restTemplate.exchange(url, HttpMethod.GET, entity, RealTimeNewsResult.class, fromCurrency, toCurrency);

        List<News> news = response.getBody().getData().getNews();

        return news;
    }
}
