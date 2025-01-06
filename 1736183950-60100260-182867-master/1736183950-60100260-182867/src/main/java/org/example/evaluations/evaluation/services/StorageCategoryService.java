package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.evaluations.evaluation.repos.CategoryRepo;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class StorageCategoryService implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category addCategory(@NotNull Category category) {

        Optional<Category> existingCaterogy = this.categoryRepo.findCategoryById(category.getId());

        if(existingCaterogy.isPresent()){
            return category;
        }

        return this.categoryRepo.save(category);
    }

    public List<Category> getAllPremiumCategories() {

        return this.categoryRepo.findCategoryByIsPremiumTrue();
    }

    public List<Category> getCategoriesBetweenIds(Long categoryId1,Long categoryId2) {

        return this.categoryRepo.findCategoryByIdBetween(categoryId1, categoryId2);
    }


    public List<Category> getCategoriesWithMatchingName(String categoryName) {

        return this.categoryRepo.findCategoryByName(categoryName);
    }
}
