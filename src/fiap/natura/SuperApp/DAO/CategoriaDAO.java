package fiap.natura.SuperApp.DAO;

import fiap.natura.SuperApp.Produto.Categoria;
import fiap.natura.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;

    // Método para inserir uma nova categoria
    public void inserir(Categoria categoria) {
        connection = ConnectionFactory.getInstance().getConnection();

        sql = "INSERT INTO Categoria_Produto (id_categoria_prod, nm_categoria) VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, categoria.getId_categoria());
            ps.setString(2, categoria.getNome_categoria());

            ps.executeUpdate();
            System.out.println("Categoria inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir categoria: " + e.getMessage());
        } finally {
            fecharConexao();
            System.out.println("Conexão fechada.");
        }
    }


    // Método para pesquisar uma categoria por ID
    public Categoria pesquisarId(int id) {
        Categoria categoria = null;
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "SELECT * FROM Categoria_Produto WHERE id_categoria_prod = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                categoria = new Categoria(rs.getInt("id_categoria_prod"), rs.getString("nm_categoria"));
            } else {
                System.out.println("Categoria não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar categoria: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return categoria;
    }

    // Método para listar todas as categorias
    public List<Categoria> listarTodos() {
        List<Categoria> categorias = new ArrayList<>();
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "SELECT * FROM Categoria_Produto";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria(rs.getInt("id_categoria_prod"), rs.getString("nm_categoria"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return categorias;
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
