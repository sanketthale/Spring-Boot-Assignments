package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.AmazonProduct;
import org.example.evaluations.evaluation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/amazon")
public class AmazonProductController {

    @Autowired
    private IProductService amazonProductService;

    //Add your APIs here.
    @GetMapping("/search")
    public ResponseEntity<List<AmazonProduct>> getProductsByName(@RequestParam("query") String searchQuery){

        List<AmazonProduct> amazonProducts = this.amazonProductService.getProductByName(searchQuery);

        return ResponseEntity.ok(amazonProducts);
    }

    @GetMapping("/products-by-category")
    public ResponseEntity<List<AmazonProduct>> getProductsByCategory(@RequestParam("categoryid") String catId){

        List<AmazonProduct> amazonProducts = this.amazonProductService.getProductByCategoryId(catId);

        return ResponseEntity.ok(amazonProducts);
    }
}
