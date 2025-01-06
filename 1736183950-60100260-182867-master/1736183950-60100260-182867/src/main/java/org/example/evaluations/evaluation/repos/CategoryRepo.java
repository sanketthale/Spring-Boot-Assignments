package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findCategoryByIsPremiumTrue();

    List<Category> findCategoryByIdBetween(Long categoryId1, Long categoryId2);

    List<Category> findCategoryByName(String categoryName);

    Optional<Category> findCategoryById(Long categoryId);


}
