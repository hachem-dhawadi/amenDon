package com.esprit.amen.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Getter
@Setter
@DynamicUpdate
public class Don {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private Double montant;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "don", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reclamation> reclamations;
}