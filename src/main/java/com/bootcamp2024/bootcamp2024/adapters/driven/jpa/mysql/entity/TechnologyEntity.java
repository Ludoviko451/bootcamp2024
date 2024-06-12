package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "technology")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TechnologyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @NotBlank(message = "El nombre no debe estar en blanco")
    private String name;

    @Column(unique = false, nullable = false)
    @NotBlank(message = "La descripción no debe estar en blanco")
    @Size(max = 90, message = "La descripción debe tener maximo 90 caracteres")
    private String description;
}
