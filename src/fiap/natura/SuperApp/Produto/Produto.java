package fiap.natura.SuperApp.Produto;

import fiap.natura.SuperApp.Produto.*;

public class Produto {

    private int id_produto;
    private String nome_produto;
    private String descricao_produto;
    private double preco_produto;
    private int estoque;
    private Categoria categoria;

    // Construtor
    public Produto(int id_produto, String nome_produto, String descricao_produto, double preco_produto, int estoque, Categoria categoria) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.descricao_produto = descricao_produto;
        this.preco_produto = preco_produto;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    // Getters e Setters
    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getDescricao_produto() {
        return descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        this.descricao_produto = descricao_produto;
    }

    public double getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(double preco_produto) {
        this.preco_produto = preco_produto;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // toString para exibir informações do produto
    @Override
    public String toString() {
        return
                "    ID do Produto: " + id_produto + " | " +
                        "    Nome do Produto: '" + nome_produto + "' | " +
                        "    Descrição: '" + descricao_produto + "' | " +
                        "    Preço: " + preco_produto + " | " +
                        "    Estoque: " + estoque + " | " +
                        "    Categoria: " + categoria;
    }


}
