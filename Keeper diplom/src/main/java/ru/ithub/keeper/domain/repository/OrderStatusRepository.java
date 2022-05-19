package ru.ithub.keeper.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ithub.keeper.domain.entity.OrderStatus;

@RepositoryRestResource
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
