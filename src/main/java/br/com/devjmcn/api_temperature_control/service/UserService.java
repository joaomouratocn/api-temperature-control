package br.com.devjmcn.api_temperature_control.service;

import br.com.devjmcn.api_temperature_control.domain.user.UserDtoRequest;
import br.com.devjmcn.api_temperature_control.domain.user.UserDtoResponse;
import br.com.devjmcn.api_temperature_control.domain.user.UserModel;
import br.com.devjmcn.api_temperature_control.infra.exception.custom.NotDataFoundException;
import br.com.devjmcn.api_temperature_control.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDtoResponse insertUser(UserDtoRequest userDtoRequest) {
        String hasPassword = passwordEncoder.encode(userDtoRequest.password());
        UserModel userModel = new UserModel(
                userDtoRequest.name(),
                userDtoRequest.email(),
                hasPassword,
                userDtoRequest.unitId()
        );
        UserModel userModelResult = userRepository.save(userModel);
        return new UserDtoResponse(
                userModelResult.getId(),
                userModelResult.getName(),
                userModelResult.getEmail(),
                userModelResult.getUnitId()
        );
    }

    public UserDtoResponse updateUser(UserDtoRequest userDtoRequest) {
        UserModel userModel = userRepository.findById(userDtoRequest.id())
                .orElseThrow(() -> new NotDataFoundException("No user Found!"));
        userModel.setName(userDtoRequest.name());
        userModel.setEmail(userDtoRequest.email());
        userModel.setUnitId(userDtoRequest.unitId());

        return new UserDtoResponse(
                userModel.getId(),
                userDtoRequest.name(),
                userDtoRequest.email(),
                userModel.getUnitId()
        );
    }

    public void deleteUser(String userId){
        UserModel userModel = userRepository.findById(userId)
                .orElseThrow(() -> new NotDataFoundException("No user found!"));
        userModel.setEnable(false);
        userRepository.save(userModel);
    }
}
