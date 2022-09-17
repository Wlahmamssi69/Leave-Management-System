package com.pfa.LeaveManagementSystem.service.Imp;
import com.pfa.LeaveManagementSystem.enums.EtatDemande;
import com.pfa.LeaveManagementSystem.enums.TypeConge;
import com.pfa.LeaveManagementSystem.exception.EntityNotFoundException;
import com.pfa.LeaveManagementSystem.exception.ErrorCodes;
import com.pfa.LeaveManagementSystem.exception.InvalidOperationException;
import com.pfa.LeaveManagementSystem.model.Conge;
import com.pfa.LeaveManagementSystem.model.Employe;
import com.pfa.LeaveManagementSystem.repo.CongeRepo;
import com.pfa.LeaveManagementSystem.repo.EmployeRepo;
import com.pfa.LeaveManagementSystem.repo.SoldeCongeRepo;
import com.pfa.LeaveManagementSystem.service.CongeService;
import com.pfa.LeaveManagementSystem.validator.CongeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class CongeServiceImp implements CongeService {

    private CongeRepo congeRepo;
    private EmployeRepo employeRepo;
    //private SoldeCongeRepo soldeRepo;

    @Autowired
    public CongeServiceImp(CongeRepo congeRepo,EmployeRepo employeRepo,SoldeCongeRepo soldeRepo) {

        this.congeRepo = congeRepo;
        this.employeRepo = employeRepo;
        //this.soldeRepo = soldeRepo;
    }


    @Override
    public List<Conge> getConges() {
        return congeRepo.findAll();
    }

    @Override
    public Conge addConge(Conge conge) {

        CongeValidator.validateConge(conge);


        Period period = Period.between(conge.getDateRetour(), conge.getDateDepart());
        int nbrJours = Math.abs(period.getDays());
        Employe employe = employeRepo.getById(conge.getEmploye().getIdEmploye());

        if (conge.getType() == TypeConge.ANNUEL_PAYE && employe.getSolde().getAnnuelPaye() < nbrJours ||
                conge.getType() == TypeConge.NON_PAYE && employe.getSolde().getNonPaye() < nbrJours ||
                conge.getType() == TypeConge.MALADIE && employe.getSolde().getMaladie() < nbrJours ||
                conge.getType() == TypeConge.MATERNITE && employe.getSolde().getMaternite() < nbrJours) {

            log.warn("Solde Insuffisant.");
            throw new InvalidOperationException("Solde Insuffisant.", ErrorCodes.CONGE_NOT_MODIFIABLE);
        }

        conge.setEmploye(employe);
        conge.setEtat(EtatDemande.EN_COURS_DE_TRAITEMENT);
        conge.setDateDemande(LocalDate.now());
        return congeRepo.save(conge);

    }
    @Override
    public Conge updateConge(Conge conge) {

        CongeValidator.validateConge(conge);
        Optional<Conge> toUpdateConge= congeRepo.findById(conge.getIdConge());


        if(!toUpdateConge.isPresent()){
            throw new EntityNotFoundException("Le conge n'existe pas.",
                    ErrorCodes.CONGE_NOT_FOUND);
        }

        conge.setEtat(EtatDemande.EN_COURS_DE_TRAITEMENT);
        conge.setDateDemande(LocalDate.now());

        return congeRepo.save(conge);
    }
    @Override
    public Conge getCongeById(Long idConge) {

        return congeRepo.findById(idConge)
                .orElseThrow(()-> new EntityNotFoundException("Le conge n'existe pas.",
                        ErrorCodes.CONGE_NOT_FOUND));
    }

    @Override
    public void deleteCongeById(Long idConge) {
        congeRepo.deleteById(idConge);
    }
    @Override
    public Conge acceptConge(Long idConge) {


        Conge AcceptedConge= congeRepo.getById(idConge);
        Employe employe=AcceptedConge.getEmploye();

        // A revoir
        if(AcceptedConge==null){
            log.error("Le conge avec id {} n'existe pas.",idConge);
            throw new EntityNotFoundException("Le conge n'existe pas.",
                    ErrorCodes.CONGE_NOT_FOUND);
        }

        if (AcceptedConge.getEtat() != EtatDemande.EN_COURS_DE_TRAITEMENT) {
            log.warn("Opération Invalide.");
            throw new InvalidOperationException("Opération Invalide.",
                    ErrorCodes.CONGE_NOT_MODIFIABLE);

        }

        AcceptedConge.setEtat(EtatDemande.ACCEPTEE);
        Period period = Period.between(AcceptedConge.getDateRetour(),AcceptedConge.getDateDepart());
        int nbrJours = Math.abs(period.getDays());

        if(AcceptedConge.getType()==TypeConge.ANNUEL_PAYE){

            employe.getSolde().setAnnuelPaye(employe.getSolde().getAnnuelPaye()-nbrJours);
            employeRepo.save(employe);

        }else if(AcceptedConge.getType()==TypeConge.NON_PAYE){

            employe.getSolde().setNonPaye(employe.getSolde().getNonPaye()-nbrJours);
            employeRepo.save(employe);

        }else if(AcceptedConge.getType()==TypeConge.MALADIE){
            employe.getSolde().setMaladie(employe.getSolde().getMaladie()-nbrJours);
            employeRepo.save(employe);

        }else if(AcceptedConge.getType()==TypeConge.MATERNITE){

            employe.getSolde().setMaternite(employe.getSolde().getMaternite()-nbrJours);
            employeRepo.save(employe);

        }

        return  congeRepo.save(AcceptedConge);
    }

    @Override
    public Conge refuseConge(Long idConge) {


        Conge RefusedConge= congeRepo.getById(idConge);

        if(RefusedConge==null){
            log.error("Le congé avec id {} n'existe pas.",idConge);
            throw new EntityNotFoundException("Le congé n'existe pas.",
                    ErrorCodes.CONGE_NOT_FOUND);
        }

        //we can't refuse this request
             if(LocalDate.now().isEqual(RefusedConge.getDateDepart()) || LocalDate.now().isAfter(RefusedConge.getDateDepart())){

            throw new InvalidOperationException("La date du congé est dèja passée",
                    ErrorCodes.CONGE_NOT_MODIFIABLE);
        }

        if (RefusedConge.getEtat() != EtatDemande.EN_COURS_DE_TRAITEMENT) {
            throw new InvalidOperationException("Opération Invalide.",
                    ErrorCodes.CONGE_NOT_MODIFIABLE);

        }
        RefusedConge.setEtat(EtatDemande.REFUSEE);
        return  congeRepo.save(RefusedConge);
    }
    @Override
    public Conge cancelConge(Long idConge) {

        //TO-DO in case we cancel after accepting request ==> update solde)
        if(idConge == null) {
            log.error("ID est null!");
            throw new EntityNotFoundException("Le congé n'existe pas.",ErrorCodes.CONGE_NOT_FOUND);
        }

        Conge CanceledConge= congeRepo.getById(idConge);
        if(CanceledConge==null){
            log.error("Le congé avec id {} n'existe pas.",idConge);
            throw new EntityNotFoundException("Le congé n'existe pas.",
                    ErrorCodes.CONGE_NOT_FOUND);
        }

        if (CanceledConge.getEtat() == EtatDemande.ANNULEE || CanceledConge.getEtat() == EtatDemande.REFUSEE) {
            log.warn("Opération Invalide.");
            throw new InvalidOperationException("Opération Invalide.",
                    ErrorCodes.CONGE_NOT_MODIFIABLE);

        }


        //check if date conge has passed InvalidOperationException
        if(LocalDate.now().isEqual(CanceledConge.getDateDepart()) || LocalDate.now().isAfter(CanceledConge.getDateDepart())){
            log.error("La date du congé est dèja passée");
            throw new InvalidOperationException("Opération Invalide.",ErrorCodes.CONGE_NOT_MODIFIABLE);
        }


        CanceledConge.setEtat(EtatDemande.ANNULEE);
        return  congeRepo.save(CanceledConge);
    }

    @Override
    public List<Conge> getHistoriqueByType(TypeConge typeConge) {
        return congeRepo.findByType(typeConge);
    }


}
