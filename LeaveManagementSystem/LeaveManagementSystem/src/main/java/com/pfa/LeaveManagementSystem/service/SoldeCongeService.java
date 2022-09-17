package com.pfa.LeaveManagementSystem.service;
import com.pfa.LeaveManagementSystem.model.SoldeConge;
import java.util.List;

public interface SoldeCongeService {

    SoldeConge addSolde(SoldeConge solde);
    SoldeConge updateSolde(SoldeConge solde);
    List<SoldeConge> getSoldes();
    void deleteById(Long idSolde);
}
