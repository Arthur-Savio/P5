package br.ufal.ic.academico.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter private int period;

    @OneToOne
    private Subject offerSubject;

    @OneToOne
    private Teacher offerTeacher;
}
