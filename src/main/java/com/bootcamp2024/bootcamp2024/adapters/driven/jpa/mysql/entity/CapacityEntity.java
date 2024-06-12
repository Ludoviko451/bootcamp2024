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
@Table(name = "capacity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapacityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Nombre es obligatorio")
    @Size(max = 50, message = "Nombre no puede superar los 30 caracteres")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Descripción es obligatoria")
    @Size(max = 90, message = "Descripción no puede superar los 90 caracteres")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "capacity_technology",
            joinColumns = @JoinColumn(name = "capacity_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private List<TechnologyEntity> technologyList;

}
