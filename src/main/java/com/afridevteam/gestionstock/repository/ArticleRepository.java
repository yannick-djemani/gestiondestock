package com.afridevteam.gestionstock.repository;

import com.afridevteam.gestionstock.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCategorieId(Long id);

    Optional<Article> findByCodeArticle(String code);

    Page<Article> findByCodeArticle(Long idArticle, Pageable pageable);
}
