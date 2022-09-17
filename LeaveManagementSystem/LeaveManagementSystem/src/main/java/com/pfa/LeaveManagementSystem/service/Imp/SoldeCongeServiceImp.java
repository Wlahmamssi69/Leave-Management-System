package com.pfa.LeaveManagementSystem.service.Imp;
import com.pfa.LeaveManagementSystem.model.SoldeConge;
import com.pfa.LeaveManagementSystem.repo.SoldeCongeRepo;
import com.pfa.LeaveManagementSystem.service.SoldeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SoldeCongeServiceImp implements SoldeCongeService {


    private SoldeCongeRepo soldeCongeRepo;

    @Autowired
    public SoldeCongeServiceImp(SoldeCongeRepo soldeCongeRepo) {

        this.soldeCongeRepo = soldeCongeRepo;
    }

    @Override
    public SoldeConge addSolde(SoldeConge solde) {
        return soldeCongeRepo.save(solde);
    }

    @Override
    public SoldeConge updateSolde(SoldeConge solde) {
        return soldeCongeRepo.save(solde);
    }

    @Override
    public List<SoldeConge> getSoldes() {
        return soldeCongeRepo.findAll();
    }

    @Override
    public void deleteById(Long idSolde) {
        soldeCongeRepo.deleteById(idSolde);
    }
}