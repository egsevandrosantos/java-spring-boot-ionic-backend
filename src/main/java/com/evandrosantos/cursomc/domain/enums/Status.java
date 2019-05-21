package com.evandrosantos.cursomc.domain.enums;

public enum Status {
    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo");

    private int cod;
    private String descricao;

    private Status(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status getEnumByCod(Integer cod) {
        if (cod == null) return null;
        for (Status x : Status.values()) {
            if (cod.equals(x.getCod())) return x;
        }
        throw new IllegalArgumentException("Status, Id inválido: " + cod);
    }
}
