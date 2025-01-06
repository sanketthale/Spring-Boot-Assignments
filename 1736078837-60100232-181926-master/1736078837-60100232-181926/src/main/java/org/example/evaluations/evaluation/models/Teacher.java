package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "teachers")
    private Set<Student> students;

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherRating> ratings;
}
