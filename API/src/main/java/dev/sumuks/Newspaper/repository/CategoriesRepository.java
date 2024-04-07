package dev.sumuks.Newspaper.repository;

import dev.sumuks.Newspaper.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
}
