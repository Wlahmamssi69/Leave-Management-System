package com.pfa.LeaveManagementSystem.service;
import com.pfa.LeaveManagementSystem.model.Role;
import com.pfa.LeaveManagementSystem.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    List<Utilisateur> getUsers();
    Utilisateur getUser(String username) ;
    Utilisateur addUser(Utilisateur user);
    Role addRole(Role role);
    void addRoleToUser(String username,String roleName);



}
