package fiap.natura.SuperApp.Produto;

public class Categoria {

    private int id_categoria;
    private String nome_categoria;

    // Construtor
    public Categoria(int id_categoria, String nome_categoria) {
        this.id_categoria = id_categoria;
        this.nome_categoria = nome_categoria;
    }

    // Getters e Setters
    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    // toString para exibir informações da categoria
    @Override
    public String toString() {
        return
                "    ID da Categoria: " + id_categoria + " | " +
                "    Nome da Categoria: '" + nome_categoria + "'";
    }


}
