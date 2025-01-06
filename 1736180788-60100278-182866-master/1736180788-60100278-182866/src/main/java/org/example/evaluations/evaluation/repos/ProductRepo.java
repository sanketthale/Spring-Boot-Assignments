package org.example.evaluations.evaluation.repos;

import jakarta.transaction.Transactional;
import org.example.evaluations.evaluation.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByName(String productName);

    void deleteById(Long id);

    void deleteAll();

    Long deleteByName(String name);

    @Transactional
    void deleteByCategoryName(String categoryName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.id = :categoryId")
    void deleteProductWhereIdMatchesCategoryId(Long categoryId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.createdAt < :retainDate")
    int retainProductsAfter(Date retainDate);



}
