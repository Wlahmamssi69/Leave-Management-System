package com.pfa.LeaveManagementSystem.repo;
import com.pfa.LeaveManagementSystem.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,Long> {

   Utilisateur findByUsername(String username);
}
