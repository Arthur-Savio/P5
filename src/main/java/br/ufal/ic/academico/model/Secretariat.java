package br.ufal.ic.academico.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Secretariat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<Course> courses;

    @OneToMany
    private List<Offer> offerList;

    @Setter private SecretariatType secretariatType;

    public Secretariat(String type) {
        this.secretariatType = SecretariatType.valueOf(type);
    }
}
