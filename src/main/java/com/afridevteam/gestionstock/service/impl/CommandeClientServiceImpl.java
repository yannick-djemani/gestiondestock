package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.LigneCommandeClient;
import com.afridevteam.gestionstock.domain.enumeration.EtatCommande;
import com.afridevteam.gestionstock.dto.CommandeClientDto;
import com.afridevteam.gestionstock.dto.LigneCommandeClientDto;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.repository.*;
import com.afridevteam.gestionstock.service.CommandeClientService;
import com.afridevteam.gestionstock.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("CommandeClientService")
public class CommandeClientServiceImpl implements CommandeClientService {
    private final CommandeClientRepository commandeClientRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;
    private final MvtStkRepository mvtStkRepository;
    private final ArticleRepository articleRepository;
    private final ClientRepository clientRepository;

    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository,
                                     MvtStkRepository mvtStkRepository, ArticleRepository articleRepository,
                                     ClientRepository clientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.mvtStkRepository = mvtStkRepository;
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public CommandeClientDto updateEtatCommande(Long idCommande, EtatCommande etatCommande) {
        return null;
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Long idCommande, BigDecimal quantite, Long idLigneCommande) {
        return null;
    }

    @Override
    public CommandeClientDto updateClient(Long idCommande, Long idClient) {
        return null;
    }

    @Override
    public CommandeClientDto updateArticle(Long idCommande, Long idNewArticle, Long idLigneCommande) {
        return null;
    }

    @Override
    public CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande) {
        return null;
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
        return null;
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
