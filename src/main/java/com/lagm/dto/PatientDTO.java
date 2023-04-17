package com.lagm.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PatientDTO {
    @EqualsAndHashCode.Include
    private Integer idPatient;


    @NotNull
    @Size(min = 3, message = "Nombre es requerido")
    private String firstName;

    @NotNull
    @Size(min = 3, message = "Apellido es requerido")
    private String lastName;
    private String dni;
    private String address;

    @Pattern(regexp = "[0-9]+")
    private String phone;

    @Email
    private String email;
}
