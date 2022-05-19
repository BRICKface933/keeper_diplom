package ru.ithub.keeper.services;

import ru.ithub.keeper.domain.entity.User;
import ru.ithub.keeper.payload.dto.UserDto;

import java.util.List;

public interface UserService {
    User getCurrent();
    UserDto getById(Long id);
    List<UserDto> getAll();
}
