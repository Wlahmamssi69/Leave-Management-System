package com.pfa.LeaveManagementSystem.controller;
import com.pfa.LeaveManagementSystem.controller.api.CongeApi;
import com.pfa.LeaveManagementSystem.enums.TypeConge;
import com.pfa.LeaveManagementSystem.model.Conge;
import com.pfa.LeaveManagementSystem.service.CongeService;
import com.pfa.LeaveManagementSystem.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static com.pfa.LeaveManagementSystem.utils.constant.CONGES_ENDPOINT;


@RestController
@RequestMapping(CONGES_ENDPOINT)

public class CongeController implements CongeApi {

    private CongeService congeService;
    private EmployeService employeService;

    @Autowired
    public CongeController(CongeService congeServic,EmployeService employeService) {
        this.congeService=congeServic;
        this.employeService= employeService;
    }


    @Override
    public ResponseEntity<List<Conge>> getConges() {
        List<Conge> allConges=congeService.getConges();

        return new ResponseEntity<>(allConges, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Conge> addConge(Conge conge) {
        Conge newConge= congeService.addConge(conge);
        return new ResponseEntity<>(newConge,HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Conge> updateConge(Conge conge) {
        Conge updatedConge=congeService.updateConge(conge);
        return new ResponseEntity<>(updatedConge, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Conge> getCongeById(Long idConge) {
        Conge conge = congeService.getCongeById(idConge);
        return new ResponseEntity<>(conge, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCongeById(Long idConge) {
        congeService.deleteCongeById(idConge);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Conge> acceptConge(Long idConge) {
        return new ResponseEntity<>(congeService.acceptConge(idConge), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Conge> refuseConge(Long idConge) {
        return new ResponseEntity<>(congeService.refuseConge(idConge), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Conge> cancelConge(Long idConge) {
        return new ResponseEntity<>(congeService.cancelConge(idConge), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Conge>> getHistoriqueByType(TypeConge type) {
        List<Conge> allConges=congeService.getHistoriqueByType(type);
        return new ResponseEntity<>(allConges, HttpStatus.OK);
    }







        /*


    @Override
    public ResponseEntity<List<Conge>> getHistoriqueBetweenDate(LocalDate startDate, LocalDate endDate) {
        List<Conge> allConges=congeService.getHistoriqueBetweenDate(startDate, endDate);

        return new ResponseEntity<>(allConges, HttpStatus.OK);
    }















     */
}
