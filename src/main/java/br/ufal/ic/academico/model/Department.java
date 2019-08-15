package br.ufal.ic.academico.model;

import io.dropwizard.validation.SizeRange;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany
    private List<Secretariat> secretariats;

    @ManyToMany
    private List<Teacher> teachers;

    public Department(String name) {
        this.name = name;
    }
}
