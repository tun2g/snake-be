package com.example.tram.modules.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends  JpaRepository<Role,Long>{

    @Query
    Role findByRoleName(String roleName);
}
