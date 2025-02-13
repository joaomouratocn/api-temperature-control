package br.com.devjmcn.api_temperature_control.controller;

import br.com.devjmcn.api_temperature_control.domain.user.UserLoginDto;
import br.com.devjmcn.api_temperature_control.domain.user.UserLoginResponseDto;
import br.com.devjmcn.api_temperature_control.repository.UserRepository;
import br.com.devjmcn.api_temperature_control.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserLoginDto userLoginDto) {
       UserLoginResponseDto userLoginResponseDto =  userService.loginUser(userLoginDto);
       return ResponseEntity.ok(userLoginResponseDto);
    }
}
