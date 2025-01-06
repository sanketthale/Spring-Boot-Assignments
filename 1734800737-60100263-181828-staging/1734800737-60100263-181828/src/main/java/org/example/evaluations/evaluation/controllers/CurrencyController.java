package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.News;
import org.example.evaluations.evaluation.services.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private ICurrencyService currencyService;

    @GetMapping("conversionNews")
    public ResponseEntity<List<News>> getCurrencyNews(
            @RequestParam("from_symbol") String fromSymbol,
            @RequestParam("to_symbol") String toSymbol) {

        List<News> news = this.currencyService.getCurrencyNews(fromSymbol, toSymbol);

        return ResponseEntity.ok(news);
    }
}
