package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.dto.EntrepriseDto;
import com.afridevteam.gestionstock.dto.RolesDto;
import com.afridevteam.gestionstock.dto.UtilisateurDto;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.repository.EntrepriseRepository;
import com.afridevteam.gestionstock.repository.RolesRepository;
import com.afridevteam.gestionstock.service.EntrepriseService;
import com.afridevteam.gestionstock.service.UtilisateurService;
import com.afridevteam.gestionstock.utils.MessageUtils;
import com.afridevteam.gestionstock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional(rollbackOn = Exception.class)
@Service("EntrepriseService")
public class EntrepriseServiceImpl implements EntrepriseService {
    private final EntrepriseRepository entrepriseRepository;
    private final UtilisateurService utilisateurService;
    private final RolesRepository rolesRepository;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService, RolesRepository rolesRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is invalid {}", dto);
            throw new InvalidEntityException("Veuillez corriger les erreurs ", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        EntrepriseDto saveEntreprise = EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(dto)));
        UtilisateurDto utilisateurDto = fromEntreprise(saveEntreprise);
        UtilisateurDto saveUser = utilisateurService.save(utilisateurDto);
        RolesDto rolesDto = RolesDto.builder()
                                    .roleName("ADMIN")
                                    .utilisateur(saveUser)
                                    .build();
        rolesRepository.save(RolesDto.toEntity(rolesDto));

        return saveEntreprise;
    }

    @Override
    public EntrepriseDto findById(Long id) {
        if (null == id) {
            log.error("l'id ne doit pas etre null");
            return null;
        }
        return entrepriseRepository.findById(id)
                                   .map(EntrepriseDto::fromEntity)
                                   .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_F, "entreprise", "l'id", id.toString()),
                                                                                  ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public EntrepriseDto findByNom(String name) {
        if (!StringUtils.hasText(name)) {
            log.error("Client name  est null");
        }
        return entrepriseRepository.findByNom(name).map(EntrepriseDto::fromEntity)
                                   .orElseThrow(() -> new EntityNotFoundException("Aucun client  avec le nom=" + name + " n'a ete trouve da la bd ",
                                                                                  ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream().map(EntrepriseDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (null == id) {
            log.error(" impossible de supprimer le l'entreprise ");
            throw new InvalidOperationException("impossible de supprimer ce client avec un id null", ErrorCodes.ENTREPRISE_NOT_FOUND);
        }
        entrepriseRepository.deleteById(id);
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto saveEntreprise) {
        return UtilisateurDto.builder()
                             .adresse(saveEntreprise.getAdresse())
                             .nom(saveEntreprise.getNom())
                             .prenom(saveEntreprise.getCodeFiscal())
                             .motDePasse(generateRandomPassword())
                             .entreprise(saveEntreprise)
                             .dateNaissance(Instant.now())
                             .photo(saveEntreprise.getPhoto())
                             .build();
    }

    private String generateRandomPassword() {
        return "@admin";
    }
}
