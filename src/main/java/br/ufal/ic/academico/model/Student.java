package br.ufal.ic.academico.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter private int registration;

    private String name;
    private String cpf;

    @Setter private int yearEntry;

    @Setter private int credit;

    @OneToMany
    private List<Subject> subjects;

    @OneToMany
    private List<Subject> oldSubjects;

    public Student(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.registration = (int)Math.random();
    }
}
