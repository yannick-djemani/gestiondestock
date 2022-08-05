package com.afridevteam.gestionstock.controller.api;

import com.afridevteam.gestionstock.dto.ArticleDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeFournisseurDto;
import com.afridevteam.gestionstock.dto.LigneVenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.afridevteam.gestionstock.utils.Constants.*;

@Api("articles")
public interface ArticleApi {


    @PostMapping(value = ARTICLE_ENPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un article", notes = "Cette methode d'enregister ou modifier un article ", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "l'article a ete cree/modifier"),
            @ApiResponse(code = 400, message = "l'article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    ArticleDto findById(@PathVariable("id") Long id);

    @GetMapping(ARTICLE_ENPOINT)
    List<ArticleDto> findAll();

    @DeleteMapping(ARTICLE_ENDPOINT_BY_ID)
    void delete(@PathVariable("id") Long id);

    ArticleDto findByCodeArticle(@PathVariable("code") String code);

    @GetMapping(ARTICLE_ENDPOINT_BY_CODE)
    List<LigneVenteDto> findHistoriqueVentes(@PathVariable("id)") Long idArticle);

    @GetMapping(ARTICLE_ENDPOINT_HISTORY_COMMANDE_CLIENT)
    List<LigneCommandeClientDto> findHistoriqueCommandClient(@PathVariable("id") Long idArticle);

    @GetMapping(ARTICLE_ENDPOINT_HISTORY_COMMANDE_FOURNISSEUR)
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseurs(@PathVariable("id") Long idArticle);

    @GetMapping(ARTICLE_ENDPOINT_BY_CATEGORY)
    List<ArticleDto> findAllArticleByCategorie(@PathVariable("id)") Long idArticle);

    @GetMapping(ARTICLE_ENDPOINT_BY_CATEGORY_PAGE)
    Page<ArticleDto> findAllArticleByCategorie(@PathVariable("id") Long idArticle, Pageable pageable);
}
