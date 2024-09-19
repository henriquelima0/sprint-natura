package fiap.natura.SuperApp.DAO;

import fiap.natura.SuperApp.Usuario;
import fiap.natura.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private Connection connection;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;

    // Método para inserir um novo usuário
    public void inserir(Usuario usuario) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "INSERT INTO USUARIO (id_usuario, nm_usuario, senha, endereco) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, usuario.getId_usuario());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEndereco());
            ps.executeUpdate();
            System.out.println("Usuário inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método para pesquisar um usuário por ID
    public Usuario pesquisarId(int id) {
        Usuario usuario = null;
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "SELECT u.id_usuario, u.nm_usuario, u.senha, u.endereco" +
                " WHERE u.id_usuario = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Criar o objeto Usuario
                usuario = new Usuario(rs.getInt("id_usuario"), rs.getString("nm_usuario"), rs.getString("senha"), rs.getString("endereco")) {
                    @Override
                    public void cadastrarUsuario(Usuario usuario) {
                        // Implementar conforme a lógica necessária
                    }
                };
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar usuário: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return usuario;
    }

    // Método para alterar um usuário
    public void alterar(Usuario usuario) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "UPDATE USUARIO SET nm_usuario = ?, senha = ? WHERE id_usuario = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getId_usuario());
            ps.setString(4,usuario.getEndereco());
            ps.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método para excluir um usuário
    public void excluir(int id) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "DELETE FROM USUARIO WHERE id_usuario = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário excluído com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
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
