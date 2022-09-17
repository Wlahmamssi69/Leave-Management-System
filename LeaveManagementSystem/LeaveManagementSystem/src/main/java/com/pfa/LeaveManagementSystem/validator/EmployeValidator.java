package com.pfa.LeaveManagementSystem.validator;
import com.pfa.LeaveManagementSystem.exception.InvalidEntityException;
import com.pfa.LeaveManagementSystem.model.Employe;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;


public class EmployeValidator {

    public static List<String> validateEmploye(Employe employe) {
        List<String> errors = new ArrayList<String>();

        if (employe == null) {

            errors.add("{fill.name}");
            errors.add("Veuillez renseigner le prenom");
            errors.add("Veuillez renseigner le numéro de téléphone");
            errors.add("Veuillez renseigner l'adresse");
            errors.add("Veuillez indiquer le manager");
            errors.add("Veuillez renseigner le solde");

        } else {

            if (!StringUtils.hasLength(employe.getNom())) {
                errors.add("Veuillez renseigner le nom");
            }

            if (!StringUtils.hasLength(employe.getPrenom())) {
                errors.add("Veuillez renseigner le prenom");
            }
            if (!StringUtils.hasLength(employe.getPhone())) {
                errors.add("Veuillez renseigner le numéro de téléphone");
            }
            if (!StringUtils.hasLength(employe.getAdresse())) {
                errors.add("Veuillez renseigner l'adresse");
            }

/*
            if (employe.getSolde() == null) {
                errors.add("Veuillez indiquer le solde");
            }*/

        }
        return errors;
    }
}
