package com.afridevteam.gestionstock.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeMotDePasseDto {
    private Long id;

    private String motDepasse;
    
    private String confirmMotDePasse;
}
