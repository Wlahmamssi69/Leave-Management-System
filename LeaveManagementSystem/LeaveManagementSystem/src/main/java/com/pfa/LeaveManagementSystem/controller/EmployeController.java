package com.pfa.LeaveManagementSystem.controller;
import com.pfa.LeaveManagementSystem.controller.api.EmployeApi;
import com.pfa.LeaveManagementSystem.model.Conge;
import com.pfa.LeaveManagementSystem.model.Employe;
import com.pfa.LeaveManagementSystem.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static com.pfa.LeaveManagementSystem.utils.constant.EMPLOYES_ENDPOINT;
import static com.pfa.LeaveManagementSystem.utils.constant.appRoot;

@RestController
@RequestMapping( EMPLOYES_ENDPOINT)

public class EmployeController implements EmployeApi {

    private EmployeService employeService;

    @Autowired
    public EmployeController(EmployeService employeService) {

        this.employeService = employeService;
    }


    @Override
    public ResponseEntity<Employe> addEmploye( Employe employe,Long idManager) {
        Employe newEmploye = employeService.addEmploye(employe,idManager);
        return new ResponseEntity<>(newEmploye, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Employe> updateEmploye( Employe employe) {
        Employe updatedEmploye = employeService.updateEmploye(employe);
        return new ResponseEntity<>(updatedEmploye, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employe>> getEmployes() {
        List<Employe> AllEmploye =employeService.getEmployes();
        return new ResponseEntity<>(AllEmploye, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employe> getEmployeById (Long idEmploye) {
        Employe employe = employeService.getEmployeById(idEmploye);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employe> findEmployeByNom(String nom) {
        Employe employe= employeService.findEmployeByNom(nom);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(Long idEmploye) {
        employeService.deleteById(idEmploye);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employe>> getEmployesListByManager(Long idManager) {
        List<Employe> employesList=employeService.getEmployesListByManager(idManager);
        return new ResponseEntity<>(employesList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Conge>> getHistoriqueByEmploye(Long idEmploye) {
        List<Conge> employesList=employeService.getHistoriqueByEmploye(idEmploye);
        return new ResponseEntity<>(employesList, HttpStatus.OK);
    }


}
