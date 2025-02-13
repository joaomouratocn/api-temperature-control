package br.com.devjmcn.api_temperature_control.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    @Column(name = "unit")
    private Integer unitId;
    @Column(name = "emit_rel")
    private Boolean emailRel;
    private Boolean enable;

    public UserModel(
            @NotNull @NotBlank String name,
            @NotNull @NotBlank String email,
            @NotNull @NotBlank String password,
            @NotBlank @NotNull Integer unitId
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.unitId = unitId;
    }
}
