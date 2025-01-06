package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Class {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String topic;

    @ManyToMany
    private Set<Batch> batches;

    @ManyToOne
    private Instructor currentInstructor;
}
