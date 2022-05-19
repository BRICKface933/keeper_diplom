package ru.ithub.keeper.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ithub.keeper.core.exceptions.NotFoundException;
import ru.ithub.keeper.domain.entity.ERole;
import ru.ithub.keeper.domain.entity.Role;
import ru.ithub.keeper.domain.repository.RoleRepository;
import ru.ithub.keeper.services.RoleService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final Map<ERole, Role> cachedRoles = new HashMap<>();

    @Override
    public Role get(ERole eRole) {
        if (!cachedRoles.containsKey(eRole)) {
            cachedRoles.put(eRole, roleRepository.findByName(eRole).orElseThrow(() -> new NotFoundException(Role.class, eRole.getId())));
        }

        return cachedRoles.get(eRole);
    }
}
