package org.example.evaluations.evaluation.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Entity
public class Instructor extends ContractualEmployee {
    private String company;
}
