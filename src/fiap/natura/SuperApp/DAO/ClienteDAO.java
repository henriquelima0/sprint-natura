package fiap.natura.SuperApp.DAO;

import fiap.natura.SuperApp.Cliente;
import fiap.natura.conexao.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection connection;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;

    // Método para inserir um novo cliente
    public void inserir(Cliente cliente) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(cliente); // Insere o usuário primeiro

        connection = ConnectionFactory.getInstance().getConnection();
        sql = "INSERT INTO Cliente (id_usuario, link_historico_compras, pref_contato, carrinho_compras) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, cliente.getId_usuario());
            ps.setString(2, cliente.getLinkHistoricoCompras());
            ps.setString(3, cliente.getPrefContato());
            ps.setString(4, cliente.getCarrinhoCompras());
            ps.executeUpdate();
            System.out.println("Cliente inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método para pesquisar um cliente por ID
    public Cliente pesquisarId(int id) {
        Cliente cliente = null;
        connection = ConnectionFactory.getInstance().getConnection();

        sql = "SELECT u.id_usuario, u.nm_usuario, u.senha, u.endereco, " +
                "cli.link_historico_compras, cli.pref_contato, cli.carrinho_compras " +
                "FROM Usuario u " +
                "JOIN Cliente cli ON u.id_usuario = cli.id_usuario " +
                "WHERE u.id_usuario = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Corrigir a chamada do construtor com todos os parâmetros necessários
                cliente = new Cliente(
                        rs.getInt("id_usuario"),
                        rs.getString("nm_usuario"),
                        rs.getString("senha"),
                        rs.getString("endereco")  // Adicione o endereço
                );
                cliente.setLinkHistoricoCompras(rs.getString("link_historico_compras"));
                cliente.setPrefContato(rs.getString("pref_contato"));
                cliente.setCarrinhoCompras(rs.getString("carrinho_compras"));
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar cliente: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return cliente;
    }


    // Método para listar todos os clientes
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        connection = ConnectionFactory.getInstance().getConnection();

        // Verifique os nomes das colunas nas tabelas USUARIO e CLIENTE
        sql = "SELECT u.id_usuario, u.nm_usuario, u.senha, u.endereco, " +
                "cli.link_historico_compras, cli.pref_contato, cli.carrinho_compras " +
                "FROM Usuario u " +
                "JOIN Cliente cli ON u.id_usuario = cli.id_usuario";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // Certifique-se de que os nomes das colunas aqui estão corretos
                Cliente cliente = new Cliente(
                        rs.getInt("id_usuario"),
                        rs.getString("nm_usuario"),
                        rs.getString("senha"),
                        rs.getString("endereco")  // Verifique se o nome da coluna 'endereco' está correto
                );
                cliente.setLinkHistoricoCompras(rs.getString("link_historico_compras"));
                cliente.setPrefContato(rs.getString("pref_contato"));
                cliente.setCarrinhoCompras(rs.getString("carrinho_compras"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return clientes;
    }


    // Método para alterar um cliente
    public void alterar(Cliente cliente) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "UPDATE Cliente SET link_historico_compras = ?, pref_contato = ?, carrinho_compras = ? WHERE id_usuario = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cliente.getLinkHistoricoCompras());
            ps.setString(2, cliente.getPrefContato());
            ps.setString(3, cliente.getCarrinhoCompras());
            ps.setInt(4, cliente.getId_usuario());
            ps.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar cliente: " + e.getMessage());
        } finally {
            fecharConexao();
        }
    }

    // Método para excluir um cliente
    public void excluir(int id) {
        connection = ConnectionFactory.getInstance().getConnection();
        sql = "DELETE FROM Cliente WHERE id_usuario = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
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
