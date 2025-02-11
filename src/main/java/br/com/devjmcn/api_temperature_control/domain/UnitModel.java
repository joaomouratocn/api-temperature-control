package br.com.devjmcn.api_temperature_control.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "unit")
public class UnitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private  String name;
}
