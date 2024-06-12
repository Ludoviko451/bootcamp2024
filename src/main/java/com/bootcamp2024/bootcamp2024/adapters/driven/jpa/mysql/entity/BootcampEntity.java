package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "bootcamp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BootcampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Nombre requerido")
    private String name;

    @Column(unique = false, nullable = false)
    @NotBlank(message = "Descripción requerida")
    @Size(max = 90, message = "La descripción debe tener máximo 90 caracteres")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "bootcamp_capacity",
            joinColumns = @JoinColumn(name = "bootcamp_id"),
            inverseJoinColumns = @JoinColumn(name = "capacity_id")
    )
    private List<CapacityEntity> capacityList;
}
