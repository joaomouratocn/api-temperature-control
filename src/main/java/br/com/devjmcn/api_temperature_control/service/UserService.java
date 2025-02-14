package br.com.devjmcn.api_temperature_control.service;

import br.com.devjmcn.api_temperature_control.domain.user.*;
import br.com.devjmcn.api_temperature_control.infra.exception.custom.InvalidCredentialException;
import br.com.devjmcn.api_temperature_control.infra.exception.custom.NotDataFoundException;
import br.com.devjmcn.api_temperature_control.infra.exception.custom.UserExistException;
import br.com.devjmcn.api_temperature_control.infra.securety.TokenService;
import br.com.devjmcn.api_temperature_control.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;

    public UserRegisterResponseDto insertUser(UserRegisterDto userRegisterDto) {
        Optional<UserModel> userFind = userRepository.findByEmail(userRegisterDto.email());
        if (userFind.isEmpty()) {
            String hasPassword = passwordEncoder.encode(userRegisterDto.password());
            UserModel userModelNew = new UserModel(
                    userRegisterDto.name(),
                    userRegisterDto.email(),
                    hasPassword,
                    userRegisterDto.unitId()
            );
            UserModel userModelResult = userRepository.save(userModelNew);
            return new UserRegisterResponseDto(
                    userModelResult.getId(),
                    userModelResult.getName(),
                    userModelResult.getEmail(),
                    userModelResult.getUnitId()
            );
        }

        throw new UserExistException("User already exists");
    }

    public UserRegisterResponseDto updateUser(UserRegisterDto userRegisterDto) {
        UserModel userModel = userRepository.findById(userRegisterDto.id())
                .orElseThrow(() -> new NotDataFoundException("No user Found!"));
        userModel.setName(userRegisterDto.name());
        userModel.setEmail(userRegisterDto.email());
        userModel.setUnitId(userRegisterDto.unitId());

        return new UserRegisterResponseDto(
                userModel.getId(),
                userRegisterDto.name(),
                userRegisterDto.email(),
                userModel.getUnitId()
        );
    }

    public void deleteUser(String userId) {
        UserModel userModel = userRepository.findById(userId)
                .orElseThrow(() -> new NotDataFoundException("No user found!"));
        userModel.setEnable(false);
        userRepository.save(userModel);
    }

//    public UserDetails findUserByEmail(String email) {
//        UserModel userModel = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
//        return User.builder()
//                .username(userModel.getEmail())
//                .password(userModel.getPassword())
//                .disabled(!isEnable(userModel.getEnable()))
//                .build();
//    }

    private boolean isEnable(Integer enable) {
        return enable != null && enable == 1;
    }

    public UserLoginResponseDto loginUser(UserLoginDto userLoginDto) {
        UserModel userModel = userRepository.findByEmail(userLoginDto.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        if (passwordEncoder.matches(userLoginDto.password(), userModel.getPassword())) {
            String token = tokenService.generateToken(userModel);
            return new UserLoginResponseDto(token, userModel.getName());
        }
        throw new InvalidCredentialException("Invalid credentials!");
    }
}
