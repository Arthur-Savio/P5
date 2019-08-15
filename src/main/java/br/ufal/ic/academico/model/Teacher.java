package br.ufal.ic.academico.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @Setter private String cpf;

    @OneToMany
    private List<Subject> subjects;

    @ManyToMany
    private List<Department> departments;

    public Teacher(String name) {
        this.name = name;
    }
}
