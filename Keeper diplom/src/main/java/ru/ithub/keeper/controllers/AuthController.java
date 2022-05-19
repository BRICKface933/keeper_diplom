package ru.ithub.keeper.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ithub.keeper.core.router.Router;
import ru.ithub.keeper.payload.ApiResponse;
import ru.ithub.keeper.payload.request.LoginRequest;
import ru.ithub.keeper.payload.request.RegistrationRequest;
import ru.ithub.keeper.payload.response.AuthenticationResponse;
import ru.ithub.keeper.services.AuthenticationService;

import javax.validation.Valid;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;

    @PostMapping(Router.Authentication.Sign.In.ROOT)
    private ApiResponse<AuthenticationResponse> signIn(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(service.authenticate(request));
    }

    @PostMapping(Router.Authentication.Sign.Up.ROOT)
    private ApiResponse<AuthenticationResponse> signUp(@Valid @RequestBody RegistrationRequest request) {
        return ApiResponse.success(service.register(request));
    }
}
