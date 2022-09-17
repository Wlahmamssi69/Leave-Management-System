package com.pfa.LeaveManagementSystem.model;
import com.pfa.LeaveManagementSystem.enums.EtatDemande;
import com.pfa.LeaveManagementSystem.enums.TypeConge;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;




@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor

public class Conge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConge;

    @DateTimeFormat(pattern = " yyyy-MM-dd")
    private LocalDate dateDepart;

    @DateTimeFormat(pattern = " yyyy-MM-dd")
    private LocalDate dateRetour;

    @DateTimeFormat(pattern = " yyyy-MM-dd")
    private LocalDate dateDemande;

    @Enumerated(EnumType.STRING)
    private TypeConge type;

    @Enumerated(EnumType.STRING)
    private EtatDemande etat;

    @ManyToOne
    @NonNull
    @JoinColumn(name="employe_id")
    private Employe employe;

}
