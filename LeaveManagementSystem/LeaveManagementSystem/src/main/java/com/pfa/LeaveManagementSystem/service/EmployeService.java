package com.pfa.LeaveManagementSystem.service;
import com.pfa.LeaveManagementSystem.model.Conge;
import com.pfa.LeaveManagementSystem.model.Employe;
import java.util.List;


public interface EmployeService {

    List<Employe> getEmployes();
    Employe addEmploye(Employe employe,Long idManager);
    public Employe updateEmploye(Employe employe);
    Employe getEmployeById(Long idEmploye);
    Employe findEmployeByNom(String nom);
    void deleteById(Long idEmploye);
    List<Employe> getEmployesListByManager(Long idManager);

    List<Conge> getHistoriqueByEmploye(Long idEmploye);

}
