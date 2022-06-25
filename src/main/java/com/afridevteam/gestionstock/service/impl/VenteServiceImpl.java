package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.Article;
import com.afridevteam.gestionstock.domain.LigneVente;
import com.afridevteam.gestionstock.domain.Vente;
import com.afridevteam.gestionstock.domain.enumeration.SourceMvtStk;
import com.afridevteam.gestionstock.domain.enumeration.TypeMvtStk;
import com.afridevteam.gestionstock.dto.ArticleDto;
import com.afridevteam.gestionstock.dto.LigneVenteDto;
import com.afridevteam.gestionstock.dto.MvtStkDto;
import com.afridevteam.gestionstock.dto.VenteDto;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.repository.ArticleRepository;
import com.afridevteam.gestionstock.repository.LigneVenteRepository;
import com.afridevteam.gestionstock.repository.MvtStkRepository;
import com.afridevteam.gestionstock.repository.VenteRepository;
import com.afridevteam.gestionstock.service.MvtStkService;
import com.afridevteam.gestionstock.service.VenteService;
import com.afridevteam.gestionstock.utils.MessageUtils;
import com.afridevteam.gestionstock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("Ventes")
@Slf4j
public class VenteServiceImpl implements VenteService {
    private final VenteRepository venteRepository;
    private final ArticleRepository articleRepository;
    private final LigneVenteRepository ligneVenteRepository;
    private final MvtStkRepository mvtStkRepository;

    private final MvtStkService mvtStkService;


    public VenteServiceImpl(VenteRepository venteRepository,
                            ArticleRepository articleRepository,
                            LigneVenteRepository ligneVenteRepository,
                            MvtStkRepository mvtStkRepository, MvtStkService mvtStkService) {
        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.mvtStkRepository = mvtStkRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        List<String> errors = VenteValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is invalid {}", dto);
            throw new InvalidEntityException("Veuillez corriger les erreurs  vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }
        List<String> articlesError = new ArrayList<>();
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (!article.isPresent()) {
                articlesError.add(String.format(MessageUtils.MESSAGE_M, "article", "id", ligneVenteDto.getArticle().getId().toString()));
            }
        });

        if (!articlesError.isEmpty()) {
            log.error("Un ou plusieurs article n'ont pas ete trouver dans la BD");
            throw new InvalidEntityException("Un ou plusieurs articles n'est pas ete trouver dans la bd ", ErrorCodes.VENTE_NOT_FOUND);
        }
        // return VenteDto.fromEntity(venteRepository.save(VenteDto.toEntity(dto)));
        Vente saveVente = venteRepository.save(VenteDto.toEntity(dto));

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(saveVente);
            ligneVenteRepository.save(ligneVente);
            updateMvStkt(ligneVente);
        });
        return VenteDto.fromEntity(saveVente);
    }

    private void updateMvStkt(LigneVente ligneVente) {
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .dateMvt(Instant.now())
                .typeMvtStk(TypeMvtStk.SORTIE)
                .sourceMvtStk(SourceMvtStk.VENTE)
                .quantite(ligneVente.getQuantite())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
        mvtStkService.sortieStock(mvtStkDto);

    }

    @Override
    public VenteDto findById(Long id) {
        if (null == id) {
            log.error("l'id ne doit pas etre null");
            return null;
        }
        return venteRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_F, "vente", "l'id", id.toString()),
                        ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll()
                .stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (null == id) {
            log.error("impossible de supprimer une vente qui n'a pas id");

        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
        if (!ligneVentes.isEmpty()) {
            throw new InvalidOperationException("impossible de supprimer la vente   id null", ErrorCodes.CLIENT_ALREADY_IN_USE);
        }
        venteRepository.deleteById(id);
    }

    @Override
    public VenteDto findByCode(String code) {
        if (null == code) {
            log.error("le code  ne doit pas etre null");
            return null;
        }
        return venteRepository.findVenteByCode(code).map(VenteDto::fromEntity).orElseThrow(() ->
                new InvalidEntityException(String.format(MessageUtils.MESSAGE_F, "vente client", "le code ", code, ErrorCodes.VENTE_NOT_VALID)));
    }
}
