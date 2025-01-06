package org.example.evaluations.evaluation.controllers;

import java.util.List;

import org.example.evaluations.evaluation.models.CashFlow;
import org.example.evaluations.evaluation.models.News;
import org.example.evaluations.evaluation.services.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private IStockService companyService;

    //Add your APIs here
    @GetMapping("/stockNews")
    public ResponseEntity<List<News>> getStockNews(@RequestParam("symbol") String stockSymbol){

        List<News> news = this.companyService.getStockNews(stockSymbol);

        return ResponseEntity.ok(news);
    }

    @GetMapping("/cashFlow")
    public ResponseEntity<List<CashFlow>> getCompanyCashFlow(@RequestParam("symbol") String stockSymbol){

        List<CashFlow> cashFlowList = this.companyService.getCompanyCashFlow(stockSymbol);

        return ResponseEntity.ok(cashFlowList);
    }
}
