package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class SoftwareDeveloper extends PermanentEmployee {
    private Long leavesTaken;
}
