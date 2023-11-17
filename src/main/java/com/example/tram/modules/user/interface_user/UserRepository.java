package com.example.tram.modules.user.interface_user;

import com.example.tram.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT count (u) > 0 FROM User u WHERE u.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT count (u) > 0 FROM User u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);


    @Query("SELECT u " +
            "FROM User u " +
            "LEFT JOIN FETCH u.userRoles ur " +
            "LEFT JOIN FETCH ur.role r " +
            "WHERE u.username = :username AND u.deletedAt is NULL")
    Optional<User> findUserAndRoleToLogin(String username);

}
