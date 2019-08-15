package br.ufal.ic.academico.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Setter private long associatedCredits;
    @Setter private long requiredCredits;
    @Setter private long period;
    @Setter private boolean required = true;

    @OneToMany
    private List<Subject> subjectsCredits;

    public Subject(String name) {
        this.name = name;
    }
}
