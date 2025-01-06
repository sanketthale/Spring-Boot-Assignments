package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.RealTimeCashFlowResult;
import org.example.evaluations.evaluation.dtos.RealTimeNewsResult;
import org.example.evaluations.evaluation.models.CashFlow;
import org.example.evaluations.evaluation.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Service
public class CompanyStockService implements IStockService {

//    @Value("${rapidapi.key}")
    private String rapidApiKey = "x-rapidapi-key";
    private String rapidApiValue = "fb4f1ffe57msh514aca2f75d9aa2p149ed6jsn2cd8cee2d239";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public List<News> getStockNews(String symbol) {

        RestTemplate restTemplate = restTemplateBuilder.build();

            String url = "https://real-time-finance-data.p.rapidapi.com/stock-news?symbol={symbol}";
            //URI uri = new URI(url);

            HttpHeaders headers = new HttpHeaders();
            headers.set(rapidApiKey, rapidApiValue);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            RealTimeNewsResult response = restTemplate.exchange(url, HttpMethod.GET, entity, RealTimeNewsResult.class, symbol).getBody();

            List<News> news = response.getData().getNews();

            return news;

    }

    public List<CashFlow> getCompanyCashFlow(String symbol) {

        RestTemplate restTemplate = this.restTemplateBuilder.build();

        String url = "https://real-time-finance-data.p.rapidapi.com/company-cash-flow?symbol={symbol}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-key", rapidApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<RealTimeCashFlowResult> response = restTemplate.exchange(url, HttpMethod.GET, entity, RealTimeCashFlowResult.class, symbol);

        List<CashFlow> cashFlows = response.getBody().getData().getCash_flow();

        return cashFlows;
    }
}
