package com.pfa.LeaveManagementSystem.repo;
import com.pfa.LeaveManagementSystem.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeRepo extends JpaRepository<Employe,Long > {


    Optional<Employe> findByNom(String nom);
}

