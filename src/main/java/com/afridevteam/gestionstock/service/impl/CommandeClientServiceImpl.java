package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.Article;
import com.afridevteam.gestionstock.domain.Client;
import com.afridevteam.gestionstock.domain.CommandeClient;
import com.afridevteam.gestionstock.domain.LigneCommandeClient;
import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.domain.enumeration.SourceMvtStk;
import com.afridevteam.gestionstock.domain.enumeration.TypeMvtStk;
import com.afridevteam.gestionstock.dto.*;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.repository.ArticleRepository;
import com.afridevteam.gestionstock.repository.ClientRepository;
import com.afridevteam.gestionstock.repository.CommandeClientRepository;
import com.afridevteam.gestionstock.repository.LigneCommandeClientRepository;
import com.afridevteam.gestionstock.service.CommandeClientService;
import com.afridevteam.gestionstock.service.MvtStkService;
import com.afridevteam.gestionstock.utils.MessageUtils;
import com.afridevteam.gestionstock.validator.CommandeClientValidator;
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
@Service("CommandeClientService")
public class CommandeClientServiceImpl implements CommandeClientService {
    private final CommandeClientRepository commandeClientRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    private final ArticleRepository articleRepository;
    private final ClientRepository clientRepository;
    private final MvtStkService mvtStkService;

    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository,
                                     ArticleRepository articleRepository,
                                     ClientRepository clientRepository, MvtStkService mvtStkService) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande) {
        checkIdCommande(idCommande);
        if (!StringUtils.hasText(String.valueOf(etatCommande))) {
            log.error("L'etat de la commande client est null");
            throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED);
        }
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        commandeClientDto.setEtatCommande(etatCommande);
        CommandeClient saveCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
        if (commandeClientDto.isCommandeLivree()) {
            updateMvtStck(idCommande);
        }
        return CommandeClientDto.fromEntity(saveCommandeClient);
    }

    private void updateMvtStck(Long idCommande) {
        List<LigneCommandeClient> lists = ligneCommandeClientRepository.findAllByCommandeClientId(idCommande);
        lists.forEach(this::effectuerSortie);
    }

    private CommandeClientDto checkEtatCommande(Long idCommande) {
        CommandeClientDto commandeClientDto = findById(idCommande);
        if (commandeClientDto.isCommandeLivree()) {
            throw new InvalidOperationException("Impossible de modifier la commande l'orsqu'elle est livrer", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED);
        }
        return commandeClientDto;
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Long idCommande, BigDecimal quantite, Long idLigneCommande) {
        checkIdCommande(idCommande);
        checkEtatCommande(idLigneCommande);
        if (null == quantite || quantite.compareTo(BigDecimal.ZERO) == 0) {
            log.error("La quantite est 0");
            throw new InvalidOperationException("Impossible de modifier la quantite de la commande avec 0", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED);
        }
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);
        // LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
        ligneCommandeClientOptional.ifPresent(ligneCommandeClient -> {
            ligneCommandeClient.setQuantite(quantite);
            ligneCommandeClientRepository.save(ligneCommandeClient);
        });


        return commandeClientDto;
    }

    private Optional<LigneCommandeClient> findLigneCommandeClient(Long idLigneCommande) {
        Optional<LigneCommandeClient> ligneCommandeClientOptional = ligneCommandeClientRepository.findById(idLigneCommande);
        if (!ligneCommandeClientOptional.isPresent()) {
            throw new EntityNotFoundException(String.format(MessageUtils.MESSAGE_F, "ligne commande client", "L'ID", idLigneCommande.toString(), ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));
        }
        return ligneCommandeClientOptional;
    }

    @Override
    public CommandeClientDto updateClient(Long idCommande, Long idClient) {
        if (null == idClient) {
            log.error("l'id du client est null");
            throw new InvalidOperationException("Imp[ossible de modifier la commande avce l'id du client null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED);
        }
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        Optional<Client> clientOptional = clientRepository.findById(idClient);

        if (!clientOptional.isPresent()) {
            throw new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "client", "L'id", idClient.toString(), ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED));
        }
        commandeClientDto.setClient(ClientDto.fromEntity(clientOptional.get()));
        return CommandeClientDto.fromEntity(commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto)));
    }

    @Override
    public CommandeClientDto updateArticle(Long idCommande, Long idNewArticle, Long idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        if (null == idNewArticle) {
            log.error("l'id de l'article est null");
            throw new InvalidOperationException("Impossible de modifier la commande avce l'id de l'article null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED);
        }
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);
        Optional<Article> articleOptional = articleRepository.findById(idNewArticle);
        if (!articleOptional.isPresent()) {
            throw new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "article", "L'id", idNewArticle.toString(), ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED));
        }
        ligneCommandeClientOptional.ifPresent(ligneCommandeClient -> {
            ligneCommandeClient.setArticle(articleOptional.get());
            ligneCommandeClientRepository.save(ligneCommandeClient);
        });
        return commandeClientDto;
    }

    @Override
    public CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);
        findLigneCommandeClient(idLigneCommande);
        ligneCommandeClientRepository.deleteById(idLigneCommande);
        return commandeClientDto;
    }

    private void checkIdCommande(Long idCommande) {
        if (null == idCommande) {
            log.error("l'id de la commande ne doit etre null");
            throw new InvalidOperationException("Impossible de modifier l'etat de  la commande avec un id null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED);
        }

    }

    private void checkIdLigneCommande(Long idLigneCommande) {
        if (null == idLigneCommande) {
            log.error("l'id de la ligne commande client ne doit etre null");
            throw new InvalidOperationException("Impossible de modifier l'etat de  la commande avec un id ligne commande null", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED);
        }
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasText("code")) {
            log.error("le code de la commande ne doit pas etre null");
            return null;
        }
        return commandeClientRepository.findByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new InvalidEntityException(String.format(MessageUtils.MESSAGE_M, "commande client ", "le code ", code.toString())));
    }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandeClientByIdLigneCommandeClient(Long idCommande) {
        return ligneCommandeClientRepository.findAllByCommandeClientId(idCommande)
                .stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("la commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide ", ErrorCodes.CLIENT_NOT_FOUND, errors);
        }
        if (dto.getId() != null && dto.isCommandeLivree()) {
            throw new InvalidEntityException("Impossible de d'enregistrer la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_CLIENT_NOT_MODIFIED, errors);
        }
        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if (!client.isPresent()) {
            log.error("Le client n'a pas ete trouve dans la bd ");
            throw new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "client", "l'ID", dto.getClient().getId(), ErrorCodes.CLIENT_NOT_FOUND));
        }
        List<String> articleErrors = new ArrayList<>();
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligneCommande -> {
                if (ligneCommande.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligneCommande.getArticle().getId());
                    if (!article.isPresent()) {
                        articleErrors.add(String.format(MessageUtils.MESSAGE_M, "article", "L'ID", ligneCommande.getArticle().getId()));
                    } else {
                        articleErrors.add("Impossible d'enregister une commande avec un article null");
                    }
                }

            });

        }
        if (!articleErrors.isEmpty()) {
            log.error("L'article n'existe pas dans la bd");
            throw new InvalidEntityException("L'article n'existe pas dans la BD", ErrorCodes.ARTICLE_NOT_FOUND);
        }
        dto.setDateCommande(Instant.now());
        CommandeClient saveCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
        if (dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligneCommande -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCommande);
                ligneCommandeClient.setCommandeClient(saveCommandeClient);
                ligneCommandeClient.setIdEntreprise(dto.getIdEntreprise());
                LigneCommandeClient saveLigneCommandeClient = ligneCommandeClientRepository.save(ligneCommandeClient);
                //effectuerSortie(saveLigneCommandeClient);
            });
        }
        return CommandeClientDto.fromEntity(saveCommandeClient);
    }

    private void effectuerSortie(LigneCommandeClient saveligneCommandeClient) {
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .article(ArticleDto.fromEntity(saveligneCommandeClient.getArticle()))
                .dateMvt(Instant.now())
                .typeMvtStk(TypeMvtStk.SORTIE)
                .sourceMvtStk(SourceMvtStk.COMMANDE_CLIENT)
                .quantite(saveligneCommandeClient.getQuantite())
                .idEntreprise(saveligneCommandeClient.getIdEntreprise())
                .build();
        mvtStkService.sortieStock(mvtStkDto);

    }

    @Override
    public CommandeClientDto findById(Long id) {
        if (id == null) {
            log.error("l'id de la commande client ");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new InvalidEntityException(String.format(MessageUtils.MESSAGE_M, "commande client ", "l'Id ", id.toString())));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll()
                .stream().map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("l'id de la commande client ");
            return;
        }
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(id);
        if (!ligneCommandeClients.isEmpty()) {
            throw new InvalidEntityException("Impossible de supprimer une commande client deja utilises", ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE);
        }
        commandeClientRepository.deleteById(id);
    }
}
