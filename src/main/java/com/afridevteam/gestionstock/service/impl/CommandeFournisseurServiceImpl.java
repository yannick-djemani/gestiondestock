package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.Article;
import com.afridevteam.gestionstock.domain.CommandeFournisseur;
import com.afridevteam.gestionstock.domain.Fournisseur;
import com.afridevteam.gestionstock.domain.LigneCommandeFournisseur;
import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.domain.enumeration.SourceMvtStk;
import com.afridevteam.gestionstock.domain.enumeration.TypeMvtStk;
import com.afridevteam.gestionstock.dto.*;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.repository.*;
import com.afridevteam.gestionstock.service.CommandeFournisseurService;
import com.afridevteam.gestionstock.service.MvtStkService;
import com.afridevteam.gestionstock.utils.MessageUtils;
import com.afridevteam.gestionstock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("CommandeFournisseurService")
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final ArticleRepository articleRepository;
    private final LigneCommandeFounisseurRepository ligneCommandeFounisseurRepository;
    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;
    private final MvtStkService mvtStkService;

    public CommandeFournisseurServiceImpl(ArticleRepository articleRepository, LigneCommandeFounisseurRepository ligneCommandeFounisseurRepository,
                                          CommandeClientRepository commandeClientRepository,
                                          CommandeFournisseurRepository commandeFournisseurRepository, FournisseurRepository fournisseurRepository,
                                          MvtStkService mvtStkService) {
        this.articleRepository = articleRepository;
        this.ligneCommandeFounisseurRepository = ligneCommandeFounisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.mvtStkService = mvtStkService;
    }


    @Override
    public CommandeFournisseurDto updateEtatCommande(Long idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);
        if (!StringUtils.hasText(String.valueOf(etatCommande))) {
            log.error("l'etat de la commande fournisseur est null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIED);
        }
        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        commandeFournisseurDto.setEtatCommande(etatCommande);

        CommandeFournisseur saveCommandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

        if (commandeFournisseurDto.isCommandeLivree()) {
            updateMvtStock(idCommande);
        }
        return CommandeFournisseurDto.fromEntity(saveCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Long idCommande, BigDecimal quantite, Long idLigneCommande) {
        checkIdCommande(idCommande);
        checkidLigneCommande(idLigneCommande);

        if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
            log.error("La quantité est 0");
            throw new InvalidOperationException("Impossible de modifier la quantité de la commande avec 0", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIED);
        }

        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);

        ligneCommandeFournisseurOptional.ifPresent(ligneCommandeFournisseur -> {
            ligneCommandeFournisseur.setQuantite(quantite);
            ligneCommandeFounisseurRepository.save(ligneCommandeFournisseur);
        });

        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Long idCommande, Long idFournisseur) {
        checkIdCommande(idCommande);
        if (null == idFournisseur) {
            log.error("L'id du Fournisseur est null");
            throw new InvalidOperationException("Impossible de modifier la commande avec l'ID fournisseur null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIED);
        }

        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFournisseur);
        if (!fournisseurOptional.isPresent()) {
            throw new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "Fournisseur", "l'ID", idFournisseur.toString(), ErrorCodes.FOURNISSEUR_NOT_FOUND));
        }
        commandeFournisseurDto.setFournisseur(FournisseurDto.fromEntity(fournisseurOptional.get()));
        return CommandeFournisseurDto.fromEntity(commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto)));
    }

    @Override
    public CommandeFournisseurDto updateArticle(Long idCommande, Long idNewArticle, Long idLigneCommande) {
        checkIdCommande(idCommande);
        checkidLigneCommande(idLigneCommande);

        if (null == idNewArticle) {
            log.error("L'ID de l'article est null");
            throw new InvalidOperationException("Impossible de modifier une commande avec l'id de l'article null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIED);
        }

        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);
        Optional<Article> articleOptional = articleRepository.findById(idNewArticle);
        if (!articleOptional.isPresent()) {
            throw new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "Article", "l'ID", idNewArticle.toString(), ErrorCodes.ARTICLE_NOT_FOUND));
        }

        ligneCommandeFournisseurOptional.ifPresent(ligneCommandeFournisseur -> {
            ligneCommandeFournisseur.setArticle(articleOptional.get());
            ligneCommandeFounisseurRepository.save(ligneCommandeFournisseur);
        });
        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Long idCommande, Long idLigneCommande) {
        checkIdCommande(idCommande);
        checkidLigneCommande(idLigneCommande);

        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);
        findLigneCommandeFournisseur(idLigneCommande);
        ligneCommandeFounisseurRepository.deleteById(idLigneCommande);

        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {

        if (!StringUtils.hasText(code)) {
            log.error("le code  ne doit pas etre  null");

            return null;
        }
        return commandeFournisseurRepository.findByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "commande fournisseur", "le code", code)));
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByIdLigneCommandeFournisseur(Long id) {
//        return ligneCommandeFounisseurRepository.findAllByCommandeFournisseurId(idCommande)
//                .stream()
//                .map(LigneCommandeFournisseurDto::fromEntity)
//                .collect(Collectors.toList());
        return null;
    }


    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("La commande fournisseur n'est pas valide {}", dto);
            throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }
        if (dto.getId() != null && dto.isCommandeLivree()) {
            throw new InvalidEntityException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIED);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
        if (!fournisseur.isPresent()) {
            log.error("Le fournisseur n'a pas été trouvé dans la BD");
            throw new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "client", "l'ID", dto.getFournisseur().getId(), ErrorCodes.CLIENT_NOT_FOUND));
        }

        List<String> articleErors = new ArrayList<>();
        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(ligneCommande -> {
                if (ligneCommande.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligneCommande.getArticle().getId());
                    if (!article.isPresent()) {
                        articleErors.add(String.format(MessageUtils.MESSAGE_M, "article", "l'id", ligneCommande.getArticle().getId().toString()));
                    }
                } else {
                    articleErors.add("Impossible d'enregistrer une commande avec un article null");
                }
            });
        }

        if (!articleErors.isEmpty()) {
            log.error("L'article n'existe pas dans la BD");
            throw new InvalidEntityException("L'article n'existe pas dans la BD", ErrorCodes.ARTICLE_NOT_FOUND);
        }
        dto.setDateCommande(Instant.now());

        CommandeFournisseur saveCommandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if (dto.getLigneCommandeFournisseurs() != null) {
            dto.getLigneCommandeFournisseurs().forEach(ligneCommande -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligneCommande);
                ligneCommandeFournisseur.setCommandeFournisseur(saveCommandeFournisseur);
                ligneCommandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
                LigneCommandeFournisseur saveLigneCommandeFournisseur = ligneCommandeFounisseurRepository.save(ligneCommandeFournisseur);

                //effectuerSortie ( saveLigneCommandeClient );
            });
        }
        return CommandeFournisseurDto.fromEntity(saveCommandeFournisseur);
    }


    @Override
    public CommandeFournisseurDto findById(Long id) {

        if (null == id) {
            log.error("l'id de la commande fournisseur ne doit pas etre null");

            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_F, "commande fournisseur", "l'id", id.toString()), ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {

        return commandeFournisseurRepository.findAll()
                .stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (null == id) {
            log.error("l'id de la commande fournisseur ne doit pas etre null");
            return;
        }

        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFounisseurRepository.findAllByCommandeFournisseurId(id);
        if (!ligneCommandeFournisseurs.isEmpty()) {
            throw new InvalidEntityException("Impossible de supprimer une commande fournisseur deja utilisé", ErrorCodes.COMMANDE_FOURNISSEUR_ALREADY_IN_USE);
        }
        commandeFournisseurRepository.deleteById(id);

    }

    private void checkIdCommande(Long idCommande) {
        if (null == idCommande) {
            log.error("l'id de la commande client ne doit pas etre null");
            throw new InvalidEntityException("Impossible de modifier l'etat d'une commande qui a un id null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }
    }

    private CommandeFournisseurDto checkEtatCommande(Long idCommande) {
        CommandeFournisseurDto commandeFournisseurDto = findById(idCommande);
        if (commandeFournisseurDto.isCommandeLivree()) {
            throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livréé", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
        }
        return commandeFournisseurDto;
    }

    private void updateMvtStock(Long idCommande) {
        List<LigneCommandeFournisseur> list = ligneCommandeFounisseurRepository.findAllByCommandeFournisseurId(idCommande);
        list.forEach(this::effectuerSortie);
    }

    private void effectuerSortie(LigneCommandeFournisseur saveLigneCommandeFournisseur) {
        MvtStkDto mvtstkDto = MvtStkDto.builder()
                .article(ArticleDto.fromEntity(saveLigneCommandeFournisseur.getArticle()))
                .dateMvt(Instant.now())
                .typeMvtStk(TypeMvtStk.SORTIE)
                .sourceMvtStk(SourceMvtStk.COMMANDE_FOURNISSEUR)
                .quantite(saveLigneCommandeFournisseur.getQuantite())
                .idEntreprise(saveLigneCommandeFournisseur.getIdEntreprise())
                .build();

        mvtStkService.sortieStock(mvtstkDto);
    }

    private Optional<LigneCommandeFournisseur> findLigneCommandeFournisseur(Long idLigneCommande) {
        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = ligneCommandeFounisseurRepository.findById(idLigneCommande);
        if (!ligneCommandeFournisseurOptional.isPresent()) {
            throw new EntityNotFoundException(String.format(MessageUtils.MESSAGE_F, "Ligne commande fournisseur", "l'ID", idLigneCommande.toString(), ErrorCodes.LIGNE_COMMANDE_FOURNISSEUR_NOT_FOUND));
        }
        return ligneCommandeFournisseurOptional;
    }

    private void checkidLigneCommande(Long idLigneCommande) {
        if (null == idLigneCommande) {
            log.error("l'id de la ligne commande fournisseur ne doit pas etre null");
            throw new InvalidEntityException("Impossible de modifier l'etat d'une commande qui a un id ligne commande null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_MODIFIED);
        }

    }
}
