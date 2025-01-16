package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.PERSIST)
    private AuthCredential authCredential;
}
