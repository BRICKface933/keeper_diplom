package ru.ithub.keeper.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.ithub.keeper.core.exceptions.NotFoundException;

@Getter
@RequiredArgsConstructor
public enum ERole {
    ROLE_USER(1L);

    private final Long id;

    public static ERole getByName(String role) {
        try {
            return valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(ERole.class, role);
        }
    }
}
