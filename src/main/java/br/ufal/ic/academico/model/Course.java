package br.ufal.ic.academico.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany
    private List<Subject> subjects;

    @OneToMany
    private List<Student> students;

    @OneToMany
    private List<Offer> offers;

    public Course(String name) {
        this.name = name;
    }
}
