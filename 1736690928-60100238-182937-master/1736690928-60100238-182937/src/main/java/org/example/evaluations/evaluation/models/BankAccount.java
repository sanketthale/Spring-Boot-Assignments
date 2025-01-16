package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

@Entity
@AttributeOverride(name = "owner", column = @Column(name = "ACCOUNT_HOLDER"))
public class BankAccount extends PurchaseDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String bankName;

    protected String number;
}
