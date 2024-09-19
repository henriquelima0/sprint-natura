package fiap.natura.SuperApp;

public class Cliente extends Usuario {

    private String linkHistoricoCompras;
    private String prefContato;
    private String carrinhoCompras;

    // Construtor atualizado
    public Cliente(int id_usuario, String nome, String senha, String endereco) {
        super(id_usuario, nome, senha, endereco);
    }

    // Getters e Setters para os atributos específicos de Cliente
    public String getLinkHistoricoCompras() {
        return linkHistoricoCompras;
    }

    public void setLinkHistoricoCompras(String linkHistoricoCompras) {
        this.linkHistoricoCompras = linkHistoricoCompras;
    }

    public String getPrefContato() {
        return prefContato;
    }

    public void setPrefContato(String prefContato) {
        this.prefContato = prefContato;
    }

    public String getCarrinhoCompras() {
        return carrinhoCompras;
    }

    public void setCarrinhoCompras(String carrinhoCompras) {
        this.carrinhoCompras = carrinhoCompras;
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {}

    @Override
    public String toString() {
        return
                "    ID do Usuário: " + getId_usuario() + " | " +
                        "    Nome: '" + getNome() + "' | " +
                        "    Senha: '" + getSenha() + "' | " +
                        "    Endereço: '" + getEndereco() + "' | " +
                        "    Link do Histórico de Compras: '" + linkHistoricoCompras + "' | " +
                        "    Preferência de Contato: '" + prefContato + "' | " +
                        "    Carrinho de Compras: '" + carrinhoCompras + "'";
    }



}
