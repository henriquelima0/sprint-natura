package fiap.natura.SuperApp.DAO;

import fiap.natura.SuperApp.Consultora;
import fiap.natura.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultoraDAO {

    private Connection connection;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;

    // Método para inserir uma nova consultora
    public void inserir(Consultora consultora) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(consultora); // Insere o usuário primeiro

        connection = ConnectionFactory.getInstance().getConnection();
        sql = "INSERT INTO Consultora (id_usuario, link_loja, apelido) VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, consultora.getId_usuario());
            ps.setString(2, consultora.getLinkLoja());
            ps.setString(3, consultora.getApelido());
            ps.executeUpdate();
            System.out.println("Consultora inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir consultora: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }


    public Consultora pesquisarId(int id) {
        Consultora consultora = null;
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "SELECT u.id_usuario, u.nm_usuario, u.senha, u.endereco, " +
                "con.link_loja, con.apelido " +
                "FROM Usuario u " +
                "JOIN Consultora con ON u.id_usuario = con.id_usuario " +
                "WHERE u.id_usuario = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Inclua os novos campos no construtor da Consultora
                consultora = new Consultora(
                        rs.getInt("id_usuario"),
                        rs.getString("nm_usuario"),
                        rs.getString("senha"),
                        rs.getString("endereco"),
                        rs.getString("link_loja"),
                        rs.getString("apelido")
                );
            } else {
                System.out.println("Consultora não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar consultora: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return consultora;
    }


    // Método para listar todas as consultoras
    public List<Consultora> listarTodos() {
        List<Consultora> consultoras = new ArrayList<>();
        connection = ConnectionFactory.getInstance().getConnection();

        // Adicionar espaçamento corretamente entre as colunas e o FROM
        sql = "SELECT u.id_usuario, u.nm_usuario, u.senha, u.endereco, " +
                "con.link_loja, con.apelido " +
                "FROM Usuario u " +
                "JOIN Consultora con ON u.id_usuario = con.id_usuario";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Certifique-se de que os parâmetros estão na ordem correta
                Consultora consultora = new Consultora(
                        rs.getInt("id_usuario"),
                        rs.getString("nm_usuario"),
                        rs.getString("senha"),
                        rs.getString("endereco"),  // Endereço
                        rs.getString("link_loja"), // Link da Loja
                        rs.getString("apelido")     // Apelido
                );
                consultoras.add(consultora);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar consultoras: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return consultoras;
    }


    // Método para alterar uma consultora
    public void alterar(Consultora consultora) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "UPDATE Consultora SET link_loja = ?, apelido = ? WHERE id_usuario = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, consultora.getLinkLoja());
            ps.setString(2, consultora.getApelido());
            ps.setInt(3, consultora.getId_usuario());
            ps.executeUpdate();
            System.out.println("Consultora atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar consultora: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método para excluir uma consultora
    public void excluir(int id) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "DELETE FROM Consultora WHERE id_usuario = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Consultora excluída com sucesso!");
            } else {
                System.out.println("Nenhuma consultora encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir consultora: " + e.getMessage());
        } finally {
            fecharConexao();
        }
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
