package dev.sumuks.Newspaper.repository;

import dev.sumuks.Newspaper.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findByCategoryOrderByCreatedOnDesc(String category);
}
