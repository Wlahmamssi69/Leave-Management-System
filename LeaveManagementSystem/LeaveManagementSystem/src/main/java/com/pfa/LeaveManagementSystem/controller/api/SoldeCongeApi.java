package com.pfa.LeaveManagementSystem.controller.api;
import com.pfa.LeaveManagementSystem.model.SoldeConge;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.pfa.LeaveManagementSystem.utils.constant.appRoot;


@Api(appRoot+"/soldes")
public interface SoldeCongeApi {

    @PostMapping("/create")
    ResponseEntity<SoldeConge> addSolde(@RequestBody SoldeConge solde);

    @PutMapping("/update")
    ResponseEntity<SoldeConge> updateSolde(@RequestBody  SoldeConge solde);

    @GetMapping("/all")
    ResponseEntity<List<SoldeConge>> getSoldes();

    @DeleteMapping("/delete/{idSolde}")
    ResponseEntity<?> deleteById(@PathVariable Long idSolde);


}
