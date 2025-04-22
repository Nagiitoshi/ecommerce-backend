package com.nagis.company.ecommerce.repository;

import com.nagis.company.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Busca usuário por email (usado para verificar duplicatas)
    boolean existsByEmail(String email);

    // Busca usuário por email (para login futuro)
    Optional<User> findByEmail(String email);

    // Busca usuários por role (método customizado)
    List<User> findByRoles_Name(String roleName);
}
