package ru.ithub.keeper.services;

import ru.ithub.keeper.payload.request.LoginRequest;
import ru.ithub.keeper.payload.request.RegistrationRequest;
import ru.ithub.keeper.payload.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(LoginRequest request);
    AuthenticationResponse register(RegistrationRequest request);
}
