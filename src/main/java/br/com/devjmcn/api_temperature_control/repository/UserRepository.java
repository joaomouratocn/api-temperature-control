package br.com.devjmcn.api_temperature_control.repository;

import br.com.devjmcn.api_temperature_control.domain.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String> {}
