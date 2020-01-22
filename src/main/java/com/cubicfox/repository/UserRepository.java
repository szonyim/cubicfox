package com.cubicfox.repository;

import com.cubicfox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>
{
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    boolean existsUserByUsername(String username);
}
