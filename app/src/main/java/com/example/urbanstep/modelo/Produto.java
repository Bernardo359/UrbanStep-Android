package com.example.urbanstep.modelo;

public class Produto {

    private int id;
    private String Nome;
    private String Descricao;
    private int Preco;
    private int Stock;
    private int Referencia;
    private int cores_id;
    private int categoria_id;
    private int imagem;


    public Produto(int id, String Nome, String Descricao, int Preco, int Stock, int Referencia, int cores_id, int categoria_id, int imagem) {
        this.id = id;
        this.Nome = Nome;
        this.Descricao = Descricao;
        this.Preco = Preco;
        this.Stock = Stock;
        this.Referencia = Referencia;
        this.cores_id = cores_id;
        this.categoria_id = categoria_id;
        this.imagem = imagem;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public int getPreco() {
        return Preco;
    }

    public int getStock() {
        return Stock;
    }

    public int getReferencia() {
        return Referencia;
    }

    public int getCores_id() {
        return cores_id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public int getimagem() {
        return imagem;
    }

    @Override
    public String toString() {
        return Nome + " - " + Preco + "€";
    }
}
