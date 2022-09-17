package com.pfa.LeaveManagementSystem.model;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long idEmploye;

    @Column(name = "nom")
    protected String nom;

    @Column(name = "prenom")
    protected String prenom;

    @Column(name = "adresse")
    protected String adresse;

    @Column(name = "phone")
    protected String Phone;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "solde_id")
    private SoldeConge solde;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private Utilisateur user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employe manager;


    @JsonIgnore
    @OneToMany(mappedBy="manager",fetch = FetchType.LAZY)
    private List<Employe> employes= new ArrayList<Employe>();

    @JsonIgnore
    @OneToMany(mappedBy = "employe",fetch = FetchType.LAZY)
    private List<Conge> conges= new ArrayList<Conge>();


    }



