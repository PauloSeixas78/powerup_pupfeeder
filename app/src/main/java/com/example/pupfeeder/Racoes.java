package com.example.pupfeeder;

public class Racoes {
    private Long id;
    private String marca;
    private Integer quantidade;
    private String tipo;
    private String porte;

    public Racoes(Long id, String marca, Integer quantidade, String tipo, String porte) {
        this.id = id;
        this.marca = marca;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.porte = porte;
    }

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPorte() {
        return porte;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }
}
