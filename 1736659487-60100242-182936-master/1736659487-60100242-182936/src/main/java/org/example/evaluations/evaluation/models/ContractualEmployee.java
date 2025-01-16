package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ContractualEmployee extends Employee {
    @Id
    private String alias;

    private  Double hourlyRenumeration;
}
