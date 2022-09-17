package com.pfa.LeaveManagementSystem.service;
import com.pfa.LeaveManagementSystem.enums.TypeConge;
import com.pfa.LeaveManagementSystem.model.Conge;
import com.pfa.LeaveManagementSystem.model.Employe;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CongeService {

    List<Conge> getConges();
    Conge addConge(Conge conge);
    public Conge updateConge(Conge conge);
    Conge getCongeById(Long id);
    void deleteCongeById(Long idConge);
    Conge acceptConge(Long idConge);
    Conge refuseConge(Long idConge);
    Conge cancelConge(Long idConge);
    List<Conge> getHistoriqueByType(TypeConge typeConge);










 /*

List<Conge> getHistoriqueBetweenDate(LocalDate startDate, LocalDate endDate);
*/

}
