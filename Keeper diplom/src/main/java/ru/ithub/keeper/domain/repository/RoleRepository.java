package ru.ithub.keeper.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ithub.keeper.domain.entity.ERole;
import ru.ithub.keeper.domain.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    boolean existsByName(ERole name);
}

