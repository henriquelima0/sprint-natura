# Projeto Natura - SuperAPP
Este é um projeto desenvolvido em Java para uma plataforma de gestão da Natura, utilizando o padrão DAO (Data Access Object) para interagir com um banco de dados Oracle. O sistema tem como objetivo gerenciar produtos, pedidos, clientes, consultoras e categorias, com foco em uma arquitetura clara e escalável.

## 📋 Funcionalidades

- **Gerenciamento de Produtos**: Cadastro, consulta e atualização de produtos no banco de dados.
- **Gerenciamento de Pedidos**: Controle de pedidos feitos pelos clientes, associando produtos e consultoras.
- **Gerenciamento de Clientes**: Registro de novos clientes e manutenção de seus dados.
- **Gerenciamento de Consultoras**: Cadastro de consultoras que intermediam a venda dos produtos.
- **Gerenciamento de Categorias**: Classificação dos produtos em categorias para facilitar a organização e busca.

## 🔨 Estrutura do Projeto

### Padrão DAO

Este projeto segue o padrão DAO para separar a lógica de acesso ao banco de dados da lógica de negócios da aplicação. Cada entidade possui uma classe DAO correspondente, responsável por realizar as operações de CRUD (Create, Read, Update, Delete).

#### Exemplo de uma Classe DAO

```java
public class ProdutoDAO {
    // Métodos para interação com o banco de dados, como:
    
    public List<Produto> listarTodos() {
        // Lógica para listar todos os produtos do banco
    }
    
    public void adicionarProduto(Produto produto) {
        // Lógica para adicionar um novo produto
    }
    
    public void atualizarProduto(Produto produto) {
        // Lógica para atualizar um produto existente
    }
    
    public void removerProduto(int id) {
        // Lógica para remover um produto pelo ID
    }
} ```

### 👥 Equipe de Desenvolvimento
* Enzo Luciano Duarte (RM: 552486)
* Murilo Santini Chequer (RM: 550198)
* João Victor Oliveira Avellar (RM: 550283)
* Ronaldo Kozan Júnior (RM: 98865)
* Francisco Henrique Lima (RM: 99545)


