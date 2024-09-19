package fiap.natura.SuperApp;

public abstract class Usuario {

    private int id_usuario;
    private String nome;
    private String senha;
    private String endereco;

    // Construtor
    public Usuario(int id_usuario, String nome, String senha, String endereco) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
    }

    // Getters e Setters
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Método abstrato que as subclasses precisam implementar
    public abstract void cadastrarUsuario(Usuario usuario);

    @Override
    public String toString() {
        return
                "    ID do Usuário: " + id_usuario + " | " +
                "    Nome: '" + nome + "' | " +
                "    Senha: '" + senha + "' | " +
                "    Endereço: '" + endereco + "' | ";
    }

}
