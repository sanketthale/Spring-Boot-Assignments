package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "AUTH_CREDENTIALS")
public class AuthCredential {
    @Id
    private String email;

    private String password;
}
