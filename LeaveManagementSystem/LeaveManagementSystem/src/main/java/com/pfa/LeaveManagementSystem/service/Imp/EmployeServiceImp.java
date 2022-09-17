package com.pfa.LeaveManagementSystem.service.Imp;
import com.pfa.LeaveManagementSystem.exception.EntityNotFoundException;
import com.pfa.LeaveManagementSystem.exception.ErrorCodes;
import com.pfa.LeaveManagementSystem.exception.InvalidEntityException;
import com.pfa.LeaveManagementSystem.model.Conge;
import com.pfa.LeaveManagementSystem.model.Employe;
import com.pfa.LeaveManagementSystem.model.Utilisateur;
import com.pfa.LeaveManagementSystem.repo.EmployeRepo;
import com.pfa.LeaveManagementSystem.repo.UtilisateurRepo;
import com.pfa.LeaveManagementSystem.service.EmployeService;
import com.pfa.LeaveManagementSystem.service.UtilisateurService;
import com.pfa.LeaveManagementSystem.validator.EmployeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;


@Service @Slf4j
public class EmployeServiceImp implements EmployeService {

    private EmployeRepo employeRepo;
    private UtilisateurService utilisateurService;

    @Autowired
    public EmployeServiceImp(EmployeRepo employeRepo,UtilisateurService utilisateurService) {

        this.employeRepo = employeRepo;
        this.utilisateurService= utilisateurService;

    }

    @Override
    public List<Employe> getEmployes() {
        return employeRepo.findAll();
    }

    @Override
    public Employe addEmploye(Employe employe, Long idManager) {

        List<String> errors = EmployeValidator.validateEmploye(employe);

        if (!errors.isEmpty()) {
            log.error("L'employé {} n'est pas valide.", employe);
            throw new InvalidEntityException("L'employé n'est pas valide.", ErrorCodes.EMPLOYE_NOT_VALID, errors);
        }

        Optional<Employe> manager = employeRepo.findById(idManager);

        if (!manager.isPresent()) {
            log.warn("Le manager n'existe pas.");
            throw new EntityNotFoundException("Le manager n'existe pas.", ErrorCodes.EMPLOYE_NOT_FOUND);
        }
        employe.setManager(manager.get());
        manager.get().getEmployes().add(employe);

        return employeRepo.save(employe);
    }

    @Override
    public Employe updateEmploye(Employe employe) {

        List<String> errors = EmployeValidator.validateEmploye(employe);
        if (!errors.isEmpty()) {
            log.error("L'employé {} n'est pas valide.", employe);
            throw new InvalidEntityException("L'employe n'est pas valide.", ErrorCodes.EMPLOYE_NOT_VALID);
        }

        return employeRepo.save(employe);
    }

    @Override
    public Employe getEmployeById(Long idEmploye) {
        if (idEmploye == null) {
            log.error("ID est null");
            return null;
        }
        return employeRepo.findById(idEmploye)
                .orElseThrow(() -> new EntityNotFoundException("L'employé n'existe pas.",
                        ErrorCodes.EMPLOYE_NOT_FOUND));
    }

    @Override
    public Employe findEmployeByNom(String nom) {
        if (!StringUtils.hasLength(nom)) {
            log.error("Le d'employé est null");
            return null;
        }
        return employeRepo.findByNom(nom)
                .orElseThrow(() -> new EntityNotFoundException("L'employé n'existe pas.", ErrorCodes.EMPLOYE_NOT_FOUND));
    }

    @Override
    public void deleteById(Long idEmploye) {
        employeRepo.deleteById(idEmploye);
    }

    @Override
    public List<Employe> getEmployesListByManager(Long idManager) {

        Optional<Employe> manager = employeRepo.findById(idManager);
        if (!manager.isPresent()) {
            log.warn("Le manager n'existe pas.");
            throw new EntityNotFoundException("Le manager n'existe pas.", ErrorCodes.EMPLOYE_NOT_FOUND);
        }

        return manager.get().getEmployes();
    }

    @Override
    public List<Conge> getHistoriqueByEmploye(Long idEmploye) {

        Optional<Employe> employe = employeRepo.findById(idEmploye);

        if (!employe.isPresent()) {
            log.warn("L'employé n'existe pas.");
            throw new EntityNotFoundException("L'employé n'existe pas.", ErrorCodes.EMPLOYE_NOT_FOUND);
        }
        return employe.get().getConges();
    }


}
