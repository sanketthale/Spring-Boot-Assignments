package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.LinkedInSearchRequest;
import org.example.evaluations.evaluation.models.LinkedInSearchItem;
import org.example.evaluations.evaluation.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/linkedInSearch")
public class LinkedInSearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping("")
    public ResponseEntity<List<LinkedInSearchItem>> searchPeople(@RequestBody LinkedInSearchRequest request){

        List<LinkedInSearchItem> searchItems = this.searchService.searchPeople(request);
        return ResponseEntity.ok(searchItems);
    }
}
