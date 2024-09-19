package fiap.natura.SuperApp.DAO;

import fiap.natura.SuperApp.Cliente;
import fiap.natura.SuperApp.Pedido;
import fiap.natura.SuperApp.Produto.*;
import fiap.natura.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private Connection connection;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;

    // Método para inserir um novo pedido
    public void inserir(Pedido pedido) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "INSERT INTO Pedido (id_pedido, dt_pedido, status, pct_desconto, id_usuario) VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pedido.getId_pedido());
            ps.setDate(2, java.sql.Date.valueOf(pedido.getDataPedido()));
            ps.setString(3, pedido.getStatus());
            ps.setInt(4, pedido.getPercentualDesconto());
            ps.setInt(5, pedido.getCliente().getId_usuario());
            ps.executeUpdate();

            // Inserir os produtos do pedido na tabela associativa ITEM_PEDIDO
            inserirProdutosPedido(pedido);
            System.out.println("Pedido inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir pedido: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método auxiliar para inserir os produtos no pedido (tabela ITEM_PEDIDO)
    private void inserirProdutosPedido(Pedido pedido) {
        sql = "INSERT INTO ITEM_PEDIDO (id_prod, id_pedido, qtd_prod, preco_prod) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            for (Produto produto : pedido.getProdutos()) {
                ps.setInt(1, produto.getId_produto());
                ps.setInt(2, pedido.getId_pedido());
                ps.setInt(3, produto.getEstoque());  // Quantidade de produtos no pedido
                ps.setDouble(4, produto.getPreco_produto());  // Preço do produto no pedido
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir produtos do pedido: " + e.getMessage());
        }
    }

    // Método para pesquisar um pedido por ID
    public Pedido pesquisarId(int id) {
        Pedido pedido = null;
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "SELECT * FROM Pedido WHERE id_pedido = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = new ClienteDAO().pesquisarId(rs.getInt("id_usuario"));
                LocalDate dataPedido = rs.getDate("dt_pedido").toLocalDate();
                String status = rs.getString("status");
                int percentualDesconto = rs.getInt("pct_desconto");

                List<Produto> produtos = listarProdutosDoPedido(id);

                pedido = new Pedido(id, dataPedido, status, percentualDesconto, cliente, produtos);
            } else {
                System.out.println("Pedido não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar pedido: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return pedido;
    }

    // Método para listar todos os produtos de um pedido
    private List<Produto> listarProdutosDoPedido(int idPedido) {
        List<Produto> produtos = new ArrayList<>();
        sql = "SELECT * FROM ITEM_PEDIDO WHERE id_pedido = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new ProdutoDAO().pesquisarId(rs.getInt("id_prod"));
                produto.setEstoque(rs.getInt("qtd_prod"));
                produto.setPreco_produto(rs.getDouble("preco_prod"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos do pedido: " + e.getMessage());
        }
        return produtos;
    }

    // Método para alterar um pedido
    public void alterar(Pedido pedido) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "UPDATE Pedido SET dt_pedido = ?, status = ?, pct_desconto = ?, id_usuario = ? WHERE id_pedido = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(pedido.getDataPedido()));
            ps.setString(2, pedido.getStatus());
            ps.setInt(3, pedido.getPercentualDesconto());
            ps.setInt(4, pedido.getCliente().getId_usuario());
            ps.setInt(5, pedido.getId_pedido());
            ps.executeUpdate();

            // Atualizar os produtos do pedido
            excluirProdutosDoPedido(pedido.getId_pedido());
            inserirProdutosPedido(pedido);  // Insere os produtos atualizados
            System.out.println("Pedido atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar pedido: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método auxiliar para excluir todos os produtos de um pedido
    private void excluirProdutosDoPedido(int idPedido) {
        sql = "DELETE FROM ITEM_PEDIDO WHERE id_pedido = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir produtos do pedido: " + e.getMessage());
        }
    }

    // Método para excluir um pedido
    public void excluir(int id) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "DELETE FROM Pedido WHERE id_pedido = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido excluído com sucesso!");
            } else {
                System.out.println("Nenhum pedido encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir pedido: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método para listar todos os pedidos
    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "SELECT * FROM Pedido";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new ClienteDAO().pesquisarId(rs.getInt("id_usuario"));
                LocalDate dataPedido = rs.getDate("dt_pedido").toLocalDate();
                String status = rs.getString("status");
                int percentualDesconto = rs.getInt("pct_desconto");
                int idPedido = rs.getInt("id_pedido");

                List<Produto> produtos = listarProdutosDoPedido(idPedido);

                Pedido pedido = new Pedido(idPedido, dataPedido, status, percentualDesconto, cliente, produtos);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return pedidos;
    }

    // Método para fechar a conexão
    private void fecharConexao() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}
