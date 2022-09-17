package com.pfa.LeaveManagementSystem.controller.api;
import com.pfa.LeaveManagementSystem.model.Conge;
import com.pfa.LeaveManagementSystem.model.Employe;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.pfa.LeaveManagementSystem.utils.constant.appRoot;


@Api(appRoot+"/employes")
public interface EmployeApi {

    @GetMapping("/all")
    ResponseEntity<List<Employe>> getEmployes();
    @PostMapping( "/create/{idManager}")
    ResponseEntity<Employe> addEmploye(@RequestBody  Employe employe,@PathVariable Long idManager);
    @PutMapping("/update")
    ResponseEntity<Employe> updateEmploye(@RequestBody  Employe employe);
    @GetMapping("id/{idEmploye}")
    ResponseEntity<Employe> getEmployeById(@PathVariable Long idEmploye);
    @GetMapping("name/{nom}")
    ResponseEntity<Employe> findEmployeByNom(@PathVariable String nom);
    @DeleteMapping("/delete/{idEmploye}")
    ResponseEntity<?> deleteById(@PathVariable Long idEmploye);
    @GetMapping("list/{idManager}")
    ResponseEntity<List<Employe>> getEmployesListByManager(@PathVariable Long idManager);
    @GetMapping("listConges/{idEmploye}")
    ResponseEntity<List<Conge>> getHistoriqueByEmploye(@PathVariable Long idEmploye);



}


