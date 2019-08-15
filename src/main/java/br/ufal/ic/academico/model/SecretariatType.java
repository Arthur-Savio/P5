package br.ufal.ic.academico.model;

public enum SecretariatType {
    GRADUATE(1), POST_GRADUATE(2);

    private final int value;
    SecretariatType(int value) {
        this.value = value;
    }
}
