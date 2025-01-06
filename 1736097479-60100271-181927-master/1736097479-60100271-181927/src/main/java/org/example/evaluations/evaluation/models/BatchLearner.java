package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "LEARNERS_PREVIOUS_BATCHES")
public class BatchLearner {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "LEARNER_ID")
    private Learner learner;

    @ManyToOne
    @JoinColumn(name="PREVIOUS_BATCH_ID")
    private Batch previous_batch;

}
