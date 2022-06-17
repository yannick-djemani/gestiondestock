package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCategorieId(Long id);
}
