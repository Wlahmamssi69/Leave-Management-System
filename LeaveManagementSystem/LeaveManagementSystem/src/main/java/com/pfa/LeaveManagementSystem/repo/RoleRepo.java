package com.pfa.LeaveManagementSystem.repo;
import com.pfa.LeaveManagementSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
