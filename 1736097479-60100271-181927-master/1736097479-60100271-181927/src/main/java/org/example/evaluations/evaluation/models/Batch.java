package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Batch {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToMany
    private List<Instructor> instructors;

    private String name;

    @ManyToMany(mappedBy = "previousBatches")
    private Set<Learner> learners = new HashSet<>();

    @ManyToMany
    private Set<Class> classes;
}
