package com.pfa.LeaveManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SoldeConge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IdSolde;
    private Long annuelPaye;
    private Long nonPaye;
    private Long maternite;
    private Long maladie;





}
