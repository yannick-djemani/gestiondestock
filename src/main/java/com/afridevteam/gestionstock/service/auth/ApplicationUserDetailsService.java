package com.afridevteam.gestionstock.service.auth;

import com.afridevteam.gestionstock.domain.auth.ExtendedUser;
import com.afridevteam.gestionstock.dto.UtilisateurDto;
import com.afridevteam.gestionstock.exception.ErrorCodes;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.repository.UtilisateurRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    public ApplicationUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateurDto = utilisateurRepository.findByEmail(email).map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new InvalidEntityException("Aucun utilisateur l'email " + email + "n'a ete trouvé dans la BD ", ErrorCodes.UTILISATEUR_NOT_FOUND));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateurDto.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new ExtendedUser(utilisateurDto.getEmail(), utilisateurDto.getMotDePasse(), utilisateurDto.getEntreprise().getId(), authorities);
    }
}
