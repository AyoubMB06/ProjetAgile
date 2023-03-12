package backend.controller;

import backend.auth.setup.AuthenticationService;
import lombok.RequiredArgsConstructor;
import openAPI.api.AuthApi;
import openAPI.model.NotOkayMessage;
import openAPI.model.TokenMessage;
import openAPI.model.XLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthApi {
    private final AuthenticationService service;

    @Override
    public ResponseEntity<TokenMessage> getToken(XLogin xlogin) {
        return ResponseEntity.ok(service.authenticate(xlogin));
    }

    @Override
    public ResponseEntity<NotOkayMessage> logout() {
        return ResponseEntity.ok(service.logout());
    }
}
