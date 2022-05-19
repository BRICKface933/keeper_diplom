package ru.ithub.keeper.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ithub.keeper.domain.entity.MaterialType;

@RepositoryRestResource
public interface MaterialTypeRepository extends JpaRepository<MaterialType, Long> {
}
