package com.pfa.LeaveManagementSystem.validator;
import com.pfa.LeaveManagementSystem.exception.ErrorCodes;
import com.pfa.LeaveManagementSystem.exception.InvalidEntityException;
import com.pfa.LeaveManagementSystem.model.Conge;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CongeValidator {

    public static void validateConge(Conge conge){

        List<String> errors = new ArrayList<String>();

        if(conge==null|| conge.getDateDepart()==null){
            errors.add("Veuillez renseigner la date de depart");
        }

        if(conge==null|| conge.getDateRetour()==null){
            errors.add("Veuillez renseigner la date de retour");
        }

        if(conge==null|| conge.getType()==null){
            errors.add("Veuillez renseigner le type de conge");
        }

        if (conge.getDateDepart()!=null && conge.getDateRetour()!=null){

            if(conge.getDateDepart().isAfter(conge.getDateRetour())){
            errors.add("la date est invalide");
            }

        }

        if(conge.getDateDepart().isBefore(LocalDate.now()) || conge.getDateDepart().isEqual(LocalDate.now())){
            errors.add("La date du Congé est dèja passée");
        }




        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le congé n'est pas valide", ErrorCodes.CONGE_NOT_VALID,errors);
        }
    }
}
