package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Learner {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private String email;

    @ManyToOne
    private Batch currentBatch;

    @ManyToMany
    private Set<Batch> previousBatches=new HashSet<>();
}
