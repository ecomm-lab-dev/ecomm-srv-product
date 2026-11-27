package com.ecomm.product.domain.enums;

import com.ecomm.product.domain.exception.InvalidCategoriaException;

public enum Categoria {
    ELETRONICOS("ELETRONICOS"),
    VESTUARIO("VESTUARIO"),
    BELEZA("BELEZA"),
    SAUDE("SAUDE"),
    ACESSORIOS("ACESSORIOS");

    private final String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public static Categoria fromString(String categoria) {
        for (Categoria c : Categoria.values()) {
            if (c.categoria.equals(categoria)) {
                return c;
            }
        }
        throw new InvalidCategoriaException(categoria);
    }
}
