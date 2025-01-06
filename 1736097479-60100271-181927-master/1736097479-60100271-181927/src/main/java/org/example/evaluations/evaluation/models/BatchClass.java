package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "BATCHES_CLASSES")
public class BatchClass {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name="class_id")
    private Class classes;

}
