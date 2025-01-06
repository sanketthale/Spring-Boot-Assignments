package org.example.evaluations.evaluation.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class TeacherRatingKey implements Serializable {

    private Long studentId;
    private Long teacherId;

    public TeacherRatingKey(){}

    public TeacherRatingKey(Long studentId, Long teacherId){
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        TeacherRatingKey that = (TeacherRatingKey) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(studentId, teacherId);
    }


}
