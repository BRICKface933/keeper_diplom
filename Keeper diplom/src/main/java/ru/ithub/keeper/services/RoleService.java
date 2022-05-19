package ru.ithub.keeper.services;

import ru.ithub.keeper.domain.entity.ERole;
import ru.ithub.keeper.domain.entity.Role;

public interface RoleService {
    Role get(ERole eRole);
}
