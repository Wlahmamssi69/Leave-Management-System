package com.pfa.LeaveManagementSystem.controller;
import com.pfa.LeaveManagementSystem.controller.api.SoldeCongeApi;
import com.pfa.LeaveManagementSystem.model.SoldeConge;
import com.pfa.LeaveManagementSystem.service.SoldeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static com.pfa.LeaveManagementSystem.utils.constant.SOLDES_ENDPOINT;



@RestController
@RequestMapping(SOLDES_ENDPOINT)

public class SoldeCongeController implements SoldeCongeApi {

    private SoldeCongeService soldeCongeService;

    @Autowired
    public SoldeCongeController(SoldeCongeService soldeCongeService) {

        this.soldeCongeService = soldeCongeService;
    }




    @Override
    public ResponseEntity<SoldeConge> addSolde(SoldeConge solde) {
        SoldeConge newSolde = soldeCongeService.addSolde(solde);
        return new ResponseEntity<>(newSolde, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<SoldeConge> updateSolde(SoldeConge solde) {
        SoldeConge updatedSolde = soldeCongeService.updateSolde(solde);
        return new ResponseEntity<>(updatedSolde, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SoldeConge>> getSoldes() {
        List<SoldeConge> allSolde=soldeCongeService.getSoldes();
        return new ResponseEntity<>(allSolde, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        soldeCongeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
