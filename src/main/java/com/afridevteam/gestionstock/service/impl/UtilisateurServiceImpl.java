package com.afridevteam.gestionstock.service.impl;

import com.afridevteam.gestionstock.domain.Utilisateur;
import com.afridevteam.gestionstock.dto.ChangeMotDePasseDto;
import com.afridevteam.gestionstock.dto.UtilisateurDto;
import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import com.afridevteam.gestionstock.repository.UtilisateurRepository;
import com.afridevteam.gestionstock.service.UtilisateurService;
import com.afridevteam.gestionstock.utils.MessageUtils;
import com.afridevteam.gestionstock.utils.SecurityUtils;
import com.afridevteam.gestionstock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("UtilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UtilisateurDto findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M,
                        "utilisateur",
                        "l'email",
                        email.toString(),
                        ErrorCodes.UTILISATEUR_NOT_FOUND)));

    }

    @Override
    public UtilisateurDto changePassword(ChangeMotDePasseDto dto) {
        validate(dto);
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(dto.getId());
        if (!optionalUtilisateur.isPresent()) {
            log.error("Aucun utilisateur n'a ete trouver avec l'id " + dto.getId());
            throw new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M, "utilisateur", "l'Id", dto.getId()),
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        Utilisateur utilisateur = optionalUtilisateur.get();

        return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }


    @Override
    public UtilisateurDto currentUser() {
        Optional<Utilisateur> current = SecurityUtils.getCurrentUserLogin().flatMap(utilisateurRepository::findByEmail);
        return current.map(UtilisateurDto::fromEntity).orElse(null);
    }


    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate2(dto);
        if (!errors.isEmpty()) {
            log.error("L'utilisateur n'est pas valide {}", dto);
            throw new InvalidEntityException(" L'utilisateur  n'est pas valide ", ErrorCodes.UTILISATEUR_NOT_FOUND, errors);
        }
        if (userAlreadyExists(dto.getEmail())) {
            throw new InvalidEntityException(" un autre utilisateur avec le meme email existe deja  ", ErrorCodes.UTILISATEUR_ALREADY_EXIST,
                    Collections.singletonList("un autre utilisateur avec le meme email existe deja dans la BD"));
        }
        dto.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(dto)));
    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (null == id) {
            log.error("l'id du fournisseur ne doit pas etre null ");
            return null;
        }
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MessageUtils.MESSAGE_M,
                        "utilisateur",
                        "l'id",
                        id.toString(),
                        ErrorCodes.UTILISATEUR_NOT_FOUND)));

    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (null == id) {
            log.error("L'id de l'utilisateur ne doit pas etre null");
            return;
        }
        utilisateurRepository.deleteById(id);

    }

    private Boolean userAlreadyExists(String email) {
        Optional<Utilisateur> user = utilisateurRepository.findByEmail(email);
        return user.isPresent();
    }

    private void validate(ChangeMotDePasseDto dto) {
        if (null == dto) {
            log.warn("impossible de modifier le mot de passe avec un objet null");
            throw new InvalidOperationException("Aucune information n'a ete fournir pour changer le mot de passe ");
        }

        if (null == dto.getId()) {
            log.warn("impossible de modifier le mot de passe avec un objet null");
            throw new InvalidOperationException("l'id de l'utilisateur est null , impossible de modifier le mot de passe",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }

        if (!StringUtils.hasText(dto.getMotDepasse()) || !StringUtils.hasText(dto.getConfirmMotDePasse())) {
            log.warn("impossible de modifier le mot de passe avec un objet null");
            throw new InvalidOperationException("Le mot de passe de l'utilisateur est incorrect  ",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }

        if (!dto.getMotDepasse().equals(dto.getConfirmMotDePasse())) {
            log.warn("Les mots de passe ne correspondent pas");
            throw new InvalidOperationException("Aucune information n'a ete fournir pour changer le mot de passe ",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
    }
}
