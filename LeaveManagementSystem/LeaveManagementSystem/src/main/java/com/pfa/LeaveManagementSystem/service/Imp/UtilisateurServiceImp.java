package com.pfa.LeaveManagementSystem.service.Imp;
import com.pfa.LeaveManagementSystem.model.Role;
import com.pfa.LeaveManagementSystem.model.Utilisateur;
import com.pfa.LeaveManagementSystem.repo.RoleRepo;
import com.pfa.LeaveManagementSystem.repo.UtilisateurRepo;
import com.pfa.LeaveManagementSystem.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @Transactional @RequiredArgsConstructor @Slf4j
public class UtilisateurServiceImp implements UtilisateurService, UserDetailsService {

    private final UtilisateurRepo utilisateurRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Utilisateur user = utilisateurRepo.findByUsername(username);
       if(user==null){
           log.error("User not found in the database");
           throw new UsernameNotFoundException("User not found in the database");
       }else{
           log.info("User found in the database : {}",username);
       }

       Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
       user.getRoles().forEach(role -> {
           authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
         });

       return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    @Override
    public List<Utilisateur> getUsers() {
        return utilisateurRepo.findAll();
    }

    @Override
    public Utilisateur getUser(String username) {
        return utilisateurRepo.findByUsername(username);
    }

    @Override
    public Utilisateur addUser(Utilisateur user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return utilisateurRepo.save(user);
    }
    @Override
    public Role addRole(Role role) {
        return roleRepo.save(role);
    }
    @Override
    public void addRoleToUser(String username, String roleName) {
        Utilisateur user=utilisateurRepo.findByUsername(username);
        Role role=roleRepo.findByRoleName(roleName);

        user.getRoles().add(role);
    }


}
