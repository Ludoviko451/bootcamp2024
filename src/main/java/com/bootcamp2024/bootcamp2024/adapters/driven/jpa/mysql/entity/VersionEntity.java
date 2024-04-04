package com.bootcamp2024.bootcamp2024.adapters.driven.jpa.mysql.entity;

import com.bootcamp2024.bootcamp2024.domain.model.Bootcamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "version")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, nullable = false)
    private int maximumCapacity;

    @Column(unique = false, nullable = false)
    private LocalDate startDate;

    @Column(unique = false, nullable = false)

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "bootcamp_id")
    private BootcampEntity bootcamp;
}
