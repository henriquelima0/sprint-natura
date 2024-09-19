package fiap.natura.SuperApp;

public class Consultora extends Usuario {

    private String linkLoja;
    private String apelido;

    public Consultora(int id_usuario, String nome, String senha, String endereco, String linkLoja, String apelido) {
        super(id_usuario, nome, senha, endereco);
        this.linkLoja = linkLoja;
        this.apelido = apelido;
    }

    public String getLinkLoja() {
        return linkLoja;
    }

    public void setLinkLoja(String linkLoja) {
        this.linkLoja = linkLoja;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    @Override
    public String toString() {
        return super.toString() +
                " | Link da Loja: " + linkLoja +
                " | Apelido: " + apelido;
    }

    @Override
    public void cadastrarUsuario(Usuario usuario) {}
}
