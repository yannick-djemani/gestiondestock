package com.afridevteam.gestionstock.dto;


import com.afridevteam.gestionstock.domain.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {

    private Long id;

    private String roleName;

    @JsonIgnore
    private UtilisateurDto utilisateur;

    public static RolesDto fromEntity(Roles roles) {
        if (roles == null) {
            return null;
        }
        return RolesDto.builder()
                       .id(roles.getId())
                       .roleName(roles.getRoleName())
                       .build();
    }

    public static Roles toEntity(RolesDto dto) {
        if (dto == null) {
            return null;
        }
        return Roles.builder().id(dto.getId())
                    .roleName(dto.getRoleName())
                    .utilisateur(UtilisateurDto.toEntity(dto.getUtilisateur())).build();
    }

}
