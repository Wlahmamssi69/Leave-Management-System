package com.pfa.LeaveManagementSystem.controller.api;
import com.pfa.LeaveManagementSystem.enums.TypeConge;
import com.pfa.LeaveManagementSystem.model.Conge;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.pfa.LeaveManagementSystem.utils.constant.appRoot;


@Api(appRoot+"/conges")
public interface CongeApi {

    @GetMapping( "/all")
    ResponseEntity<List<Conge>> getConges();
    @PostMapping("/create")
    ResponseEntity<Conge> addConge(@RequestBody Conge conge);
    @PutMapping( "/update")
    ResponseEntity<Conge> updateConge(@RequestBody Conge conge );
    @GetMapping("/{idConge}")
    ResponseEntity<Conge> getCongeById(@PathVariable Long idConge);
    @DeleteMapping("/delete/{idConge}")
    ResponseEntity<?> deleteCongeById(@PathVariable Long idConge);
    @PatchMapping("/accept/{idConge}")
    ResponseEntity<Conge> acceptConge( @PathVariable Long idConge );

    @PatchMapping("/refuse/{idConge}")
    ResponseEntity<Conge> refuseConge( @PathVariable Long idConge );

    @PatchMapping("/cancel/{idConge}")
    ResponseEntity<Conge> cancelConge( @PathVariable Long idConge );

    @GetMapping( "/historique/{type}")
    ResponseEntity<List<Conge>> getHistoriqueByType(@PathVariable TypeConge type);




           /*
    @GetMapping( "/historique/betweendate")
    ResponseEntity<List<Conge>> getHistoriqueBetweenDate( LocalDate startDate, LocalDate endDate);

*/

}
