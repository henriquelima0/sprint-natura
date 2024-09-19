package fiap.natura.SuperApp.DAO;

import fiap.natura.SuperApp.Produto.Categoria;
import fiap.natura.SuperApp.Produto.Produto;
import fiap.natura.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;

    // Método para inserir um novo produto
    public void inserir(Produto produto) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "INSERT INTO Produto (id_prod, nm_prod, descricao, valor_unit, qtd_estoque, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, produto.getId_produto());
            ps.setString(2, produto.getNome_produto());
            ps.setString(3, produto.getDescricao_produto());
            ps.setDouble(4, produto.getPreco_produto());
            ps.setInt(5, produto.getEstoque());
            ps.setInt(6, produto.getCategoria().getId_categoria()); // Chave estrangeira de Categoria
            ps.executeUpdate();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método para pesquisar um produto por ID
    public Produto pesquisarId(int id) {
        Produto produto = null;
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "SELECT * FROM Produto p JOIN Categoria_Produto c ON p.categoria_id = c.id_categoria_prod WHERE id_prod = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("id_categoria_prod"), rs.getString("nm_categoria"));
                produto = new Produto(rs.getInt("id_prod"), rs.getString("nm_prod"), rs.getString("descricao"),
                        rs.getDouble("valor_unit"), rs.getInt("qtd_estoque"), categoria);
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar produto: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return produto;
    }

    // Método para alterar um produto
    public void alterar(Produto produto) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "UPDATE Produto SET nm_prod = ?, descricao = ?, valor_unit = ?, qtd_estoque = ?, categoria_id = ? WHERE id_prod = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, produto.getNome_produto());
            ps.setString(2, produto.getDescricao_produto());
            ps.setDouble(3, produto.getPreco_produto());
            ps.setInt(4, produto.getEstoque());
            ps.setInt(5, produto.getCategoria().getId_categoria());
            ps.setInt(6, produto.getId_produto());
            ps.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método para excluir um produto
    public void excluir(int id) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "DELETE FROM Produto WHERE id_prod = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Nenhum produto encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método para listar todos os produtos
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "SELECT p.id_prod, p.nm_prod, p.descricao, p.valor_unit, p.qtd_estoque, c.id_categoria_prod, c.nm_categoria " +
                "FROM Produto p JOIN Categoria_Produto c ON p.categoria_id = c.id_categoria_prod";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("id_categoria_prod"), rs.getString("nm_categoria"));
                Produto produto = new Produto(rs.getInt("id_prod"), rs.getString("nm_prod"),
                        rs.getString("descricao"), rs.getDouble("valor_unit"),
                        rs.getInt("qtd_estoque"), categoria);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return produtos;
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
