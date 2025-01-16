package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "PERMANENT_EMPLOYEE")
public class PermanentEmployee extends Employee {
    @Id
    private String email;
    private Double costToCompany;
}
