package ru.ithub.keeper.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ithub.keeper.core.exceptions.AlreadyExistException;
import ru.ithub.keeper.core.exceptions.EPException;
import ru.ithub.keeper.domain.entity.ERole;
import ru.ithub.keeper.domain.entity.User;
import ru.ithub.keeper.domain.entity.User_;
import ru.ithub.keeper.domain.repository.RoleRepository;
import ru.ithub.keeper.domain.repository.UserRepository;
import ru.ithub.keeper.payload.request.LoginRequest;
import ru.ithub.keeper.payload.request.RegistrationRequest;
import ru.ithub.keeper.payload.response.AuthenticationResponse;
import ru.ithub.keeper.services.AuthenticationService;
import ru.ithub.keeper.services.JwtService;

import java.util.Map;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    @Override
    public AuthenticationResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken(authentication);

        //don't need to check on user exists, because authentication ll drop the exception
        User user = userRepository.findByUsername(request.getUsername()).get();

        return new AuthenticationResponse(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getRole().getName(),
                jwt
        );
    }

    @Override
    public AuthenticationResponse register(RegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AlreadyExistException(User.class, Map.of(User_.USERNAME, request.getUsername()));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMiddleName(request.getMiddleName());
        user.setRole(
                roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new EPException("Ошибка со стороны сервера, не можем найти стандартную роль :("))
        );

        userRepository.save(user);

        return authenticate(new LoginRequest(request.getUsername(), request.getPassword()));
    }
}
