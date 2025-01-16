package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

@Entity
@AttributeOverride(name = "owner", column = @Column(name="CREDIT_CARD_OWNER"))
public class CreditCard extends PurchaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String cardNumber;
    protected Long creditLimit;
}
