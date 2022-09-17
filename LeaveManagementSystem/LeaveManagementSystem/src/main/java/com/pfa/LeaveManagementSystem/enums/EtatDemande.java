package com.pfa.LeaveManagementSystem.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public enum EtatDemande {

    ACCEPTEE,
    REFUSEE,
    ANNULEE,
    EN_COURS_DE_TRAITEMENT,
    ;
    private Long id ;
    private String code;

}
