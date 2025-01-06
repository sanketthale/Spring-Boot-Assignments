package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "INSTRUCTORS_BATCHES")
public class BatchInstructor {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

}
