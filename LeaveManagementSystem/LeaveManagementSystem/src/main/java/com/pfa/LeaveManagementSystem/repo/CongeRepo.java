package com.pfa.LeaveManagementSystem.repo;

import com.pfa.LeaveManagementSystem.enums.TypeConge;
import com.pfa.LeaveManagementSystem.model.Conge;
import com.pfa.LeaveManagementSystem.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CongeRepo extends JpaRepository<Conge,Long> {
    
    List<Conge> findByType(TypeConge typeConge);


}
