package fiap.natura.SuperApp;

import fiap.natura.SuperApp.Produto.Produto;

import java.time.LocalDate;
import java.util.List;

public class Pedido {

    private int id_pedido;
    private LocalDate dataPedido;
    private String status;
    private int percentualDesconto;
    private Cliente cliente;
    private List<Produto> produtos;

    // Construtor
    public Pedido(int id_pedido, LocalDate dataPedido, String status, int percentualDesconto, Cliente cliente, List<Produto> produtos) {
        this.id_pedido = id_pedido;
        this.dataPedido = dataPedido;
        this.status = status;
        this.percentualDesconto = percentualDesconto;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    // Getters e Setters
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(int percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return
                "    ID do Pedido: " + id_pedido + " | " +
                        "    Data do Pedido: '" + dataPedido + "' | " +
                        "    Status: '" + status + "' | " +
                        "    Percentual de Desconto: " + percentualDesconto + " | " +
                        "    Cliente: " + cliente + " | " +
                        "    Produtos: " + produtos;
    }


}
