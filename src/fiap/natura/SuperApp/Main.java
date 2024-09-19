package fiap.natura.SuperApp;

import fiap.natura.SuperApp.DAO.*;
import fiap.natura.SuperApp.Produto.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static ConsultoraDAO consultoraDAO = new ConsultoraDAO();
    private static PedidoDAO pedidoDAO = new PedidoDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static CategoriaDAO categoriaDAO = new CategoriaDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar Categorias");
            System.out.println("2. Gerenciar Produtos");
            System.out.println("3. Gerenciar Clientes");
            System.out.println("4. Gerenciar Consultoras");
            System.out.println("5. Gerenciar Pedidos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir o "\n"

            switch (opcao) {
                case 2:
                    menuProdutos();
                    break;
                case 1:
                    menuCategorias();
                    break;
                case 3:
                    menuClientes();
                    break;
                case 4:
                    menuConsultoras();
                    break;
                case 5:
                    menuPedidos();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Menu para gerenciamento de produtos
    private static void menuProdutos() {
        System.out.println("\n=== MENU DE PRODUTOS ===");
        System.out.println("1. Inserir Produto");
        System.out.println("2. Pesquisar Produto");
        System.out.println("3. Alterar Produto");
        System.out.println("4. Excluir Produto");
        System.out.println("5. Listar Todos os Produtos");
        System.out.println("6. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o "\n"

        switch (opcao) {
            case 1:
                inserirProduto();
                break;
            case 2:
                pesquisarProduto();
                break;
            case 3:
                alterarProduto();
                break;
            case 4:
                excluirProduto();
                break;
            case 5:
                listarProdutos();
                break;
            case 6:
                return; // Voltar ao menu principal
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void inserirProduto() {
        System.out.println("\n=== Inserir Produto ===");
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do Produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço do Produto: ");
        double preco = scanner.nextDouble();
        System.out.print("Quantidade em Estoque: ");
        int estoque = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID da Categoria: ");
        int idCategoria = scanner.nextInt();
        Categoria categoria = categoriaDAO.pesquisarId(idCategoria);

        if (categoria == null) {
            System.out.println("Categoria inválida.");
            return;
        }

        Produto produto = new Produto(id, nome, descricao, preco, estoque, categoria);
        produtoDAO.inserir(produto);
    }

    private static void pesquisarProduto() {
        System.out.println("\n=== Pesquisar Produto ===");
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        Produto produto = produtoDAO.pesquisarId(id);
        if (produto != null) {
            System.out.println(produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void alterarProduto() {
        System.out.println("\n=== Alterar Produto ===");
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Produto produto = produtoDAO.pesquisarId(id);

        if (produto != null) {
            System.out.print("Novo Nome do Produto: ");
            String nome = scanner.nextLine();
            System.out.print("Nova Descrição do Produto: ");
            String descricao = scanner.nextLine();
            System.out.print("Novo Preço do Produto: ");
            double preco = scanner.nextDouble();
            System.out.print("Nova Quantidade em Estoque: ");
            int estoque = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nova ID da Categoria: ");
            int idCategoria = scanner.nextInt();
            Categoria categoria = categoriaDAO.pesquisarId(idCategoria);

            if (categoria == null) {
                System.out.println("Categoria inválida.");
                return;
            }

            produto.setNome_produto(nome);
            produto.setDescricao_produto(descricao);
            produto.setPreco_produto(preco);
            produto.setEstoque(estoque);
            produto.setCategoria(categoria);

            produtoDAO.alterar(produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void excluirProduto() {
        System.out.println("\n=== Excluir Produto ===");
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        produtoDAO.excluir(id);
    }

    private static void listarProdutos() {
        System.out.println("\n=== Listar Todos os Produtos ===");
        List<Produto> produtos = produtoDAO.listarTodos();
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    // Menu para gerenciamento de categorias
    private static void menuCategorias() {
        System.out.println("\n=== MENU DE CATEGORIAS ===");
        System.out.println("1. Inserir Categoria");
        System.out.println("2. Pesquisar Categoria");
        System.out.println("3. Listar Todas as Categorias");
        System.out.println("4. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o "\n"

        switch (opcao) {
            case 1:
                inserirCategoria();
                break;
            case 2:
                pesquisarCategoria();
                break;
            case 3:
                listarCategorias();
                break;
            case 4:
                return; // Voltar ao menu principal
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void inserirCategoria() {
        System.out.println("\n=== Inserir Categoria ===");
        System.out.print("ID da Categoria: ");
        int id_categoria = scanner.nextInt();
        scanner.nextLine();  // Consumir o '\n' deixado pelo nextInt()
        System.out.print("Nome da Categoria: ");
        String nome_categoria = scanner.nextLine();

        Categoria categoria = new Categoria(id_categoria, nome_categoria);
        categoriaDAO.inserir(categoria);
    }



    private static void pesquisarCategoria() {
        System.out.println("\n=== Pesquisar Categoria ===");
        System.out.print("ID da Categoria: ");
        int id = scanner.nextInt();
        Categoria categoria = categoriaDAO.pesquisarId(id);
        if (categoria != null) {
            System.out.println(categoria);
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    private static void listarCategorias() {
        System.out.println("\n=== Listar Todas as Categorias ===");
        List<Categoria> categorias = categoriaDAO.listarTodos();
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }

    private static void menuClientes() {
        System.out.println("\n=== MENU DE CLIENTES ===");
        System.out.println("1. Inserir Cliente");
        System.out.println("2. Pesquisar Cliente");
        System.out.println("3. Alterar Cliente");
        System.out.println("4. Excluir Cliente");
        System.out.println("5. Listar Todos os Clientes");
        System.out.println("6. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o "\n"

        switch (opcao) {
            case 1:
                inserirCliente();
                break;
            case 2:
                pesquisarCliente();
                break;
            case 3:
                alterarCliente();
                break;
            case 4:
                excluirCliente();
                break;
            case 5:
                listarClientes();
                break;
            case 6:
                return; // Voltar ao menu principal
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void inserirCliente() {
        System.out.println("\n=== Inserir Cliente ===");
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Cliente cliente = new Cliente(id, nome, senha, endereco);
        clienteDAO.inserir(cliente);
    }

    private static void pesquisarCliente() {
        System.out.println("\n=== Pesquisar Cliente ===");
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt();
        Cliente cliente = clienteDAO.pesquisarId(id);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void alterarCliente() {
        System.out.println("\n=== Alterar Cliente ===");
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Cliente cliente = clienteDAO.pesquisarId(id);

        if (cliente != null) {
            System.out.print("Novo Nome do Cliente: ");
            String nome = scanner.nextLine();
            System.out.print("Nova Senha: ");
            String senha = scanner.nextLine();
            System.out.print("Novo Endereço: ");
            String endereco = scanner.nextLine();

            cliente.setNome(nome);
            cliente.setSenha(senha);
            cliente.setEndereco(endereco);
            clienteDAO.alterar(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void excluirCliente() {
        System.out.println("\n=== Excluir Cliente ===");
        System.out.print("ID do Cliente: ");
        int id = scanner.nextInt();
        clienteDAO.excluir(id);
    }

    private static void listarClientes() {
        System.out.println("\n=== Listar Todos os Clientes ===");
        List<Cliente> clientes = clienteDAO.listarTodos();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }



    // Menu para gerenciamento de consultoras
    private static void menuConsultoras() {
        System.out.println("\n=== MENU DE CONSULTORAS ===");
        System.out.println("1. Inserir Consultora");
        System.out.println("2. Pesquisar Consultora");
        System.out.println("3. Alterar Consultora");
        System.out.println("4. Excluir Consultora");
        System.out.println("5. Listar Todas as Consultoras");
        System.out.println("6. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                inserirConsultora();
                break;
            case 2:
                pesquisarConsultora();
                break;
            case 3:
                alterarConsultora();
                break;
            case 4:
                excluirConsultora();
                break;
            case 5:
                listarConsultoras();
                break;
            case 6:
                return; // Voltar ao menu principal
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void inserirConsultora() {
        System.out.println("\n=== Inserir Consultora ===");
        System.out.print("ID da Consultora: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome da Consultora: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Link da Loja: ");
        String linkLoja = scanner.nextLine();
        System.out.print("Apelido: ");
        String apelido = scanner.nextLine();

        Consultora consultora = new Consultora(id, nome, senha, endereco, linkLoja, apelido);
        consultoraDAO.inserir(consultora);
    }

    private static void pesquisarConsultora() {
        System.out.println("\n=== Pesquisar Consultora ===");
        System.out.print("ID da Consultora: ");
        int id = scanner.nextInt();
        Consultora consultora = consultoraDAO.pesquisarId(id);
        if (consultora != null) {
            System.out.println(consultora);
        } else {
            System.out.println("Consultora não encontrada.");
        }
    }

    private static void alterarConsultora() {
        System.out.println("\n=== Alterar Consultora ===");
        System.out.print("ID da Consultora: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Consultora consultora = consultoraDAO.pesquisarId(id);

        if (consultora != null) {
            System.out.print("Novo Link da Loja: ");
            String linkLoja = scanner.nextLine();
            System.out.print("Novo Apelido: ");
            String apelido = scanner.nextLine();

            consultora.setLinkLoja(linkLoja);
            consultora.setApelido(apelido);

            consultoraDAO.alterar(consultora);
        } else {
            System.out.println("Consultora não encontrada.");
        }
    }

    private static void excluirConsultora() {
        System.out.println("\n=== Excluir Consultora ===");
        System.out.print("ID da Consultora: ");
        int id = scanner.nextInt();
        consultoraDAO.excluir(id);
    }

    private static void listarConsultoras() {
        System.out.println("\n=== Listar Todas as Consultoras ===");
        List<Consultora> consultoras = consultoraDAO.listarTodos();
        for (Consultora consultora : consultoras) {
            System.out.println(consultora);
        }
    }



    // Menu para gerenciamento de pedidos
    private static void menuPedidos() {
        System.out.println("\n=== MENU DE PEDIDOS ===");
        System.out.println("1. Inserir Pedido");
        System.out.println("2. Pesquisar Pedido");
        System.out.println("3. Alterar Pedido");
        System.out.println("4. Excluir Pedido");
        System.out.println("5. Listar Todos os Pedidos");
        System.out.println("6. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir o "\n"

        switch (opcao) {
            case 1:
                inserirPedido();
                break;
            case 2:
                pesquisarPedido();
                break;
            case 3:
                alterarPedido();
                break;
            case 4:
                excluirPedido();
                break;
            case 5:
                listarPedidos();
                break;
            case 6:
                return; // Voltar ao menu principal
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void inserirPedido() {
        System.out.println("\n=== Inserir Pedido ===");
        System.out.print("ID do Pedido: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Data do Pedido (YYYY-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        System.out.print("Status do Pedido: ");
        String status = scanner.nextLine();
        System.out.print("Percentual de Desconto: ");
        int desconto = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ID do Cliente: ");
        int clienteId = scanner.nextInt();
        Cliente cliente = clienteDAO.pesquisarId(clienteId);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        // Coletar os produtos para o pedido
        List<Produto> produtos = new ArrayList<>();
        char adicionarProduto;
        do {
            System.out.print("ID do Produto: ");
            int idProduto = scanner.nextInt();
            Produto produto = produtoDAO.pesquisarId(idProduto);
            if (produto != null) {
                System.out.print("Quantidade do Produto: ");
                int quantidade = scanner.nextInt();
                produto.setEstoque(quantidade);
                produtos.add(produto);
            } else {
                System.out.println("Produto não encontrado.");
            }
            System.out.print("Deseja adicionar outro produto? (s/n): ");
            adicionarProduto = scanner.next().charAt(0);
            scanner.nextLine(); // Consumir o "\n"
        } while (adicionarProduto == 's');

        Pedido pedido = new Pedido(id, data, status, desconto, cliente, produtos);
        pedidoDAO.inserir(pedido);
    }

    private static void pesquisarPedido() {
        System.out.println("\n=== Pesquisar Pedido ===");
        System.out.print("ID do Pedido: ");
        int id = scanner.nextInt();
        Pedido pedido = pedidoDAO.pesquisarId(id);
        if (pedido != null) {
            System.out.println(pedido);
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    private static void alterarPedido() {
        System.out.println("\n=== Alterar Pedido ===");
        System.out.print("ID do Pedido: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Pedido pedido = pedidoDAO.pesquisarId(id);

        if (pedido != null) {
            System.out.print("Nova Data do Pedido (YYYY-MM-DD): ");
            LocalDate data = LocalDate.parse(scanner.nextLine());
            System.out.print("Novo Status: ");
            String status = scanner.nextLine();
            System.out.print("Novo Percentual de Desconto: ");
            int desconto = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Novo ID do Cliente: ");
            int clienteId = scanner.nextInt();
            Cliente cliente = clienteDAO.pesquisarId(clienteId);

            if (cliente == null) {
                System.out.println("Cliente inválido.");
                return;
            }

            // Atualizar os produtos do pedido
            List<Produto> produtos = new ArrayList<>();
            char adicionarProduto;
            do {
                System.out.print("ID do Produto: ");
                int idProduto = scanner.nextInt();
                Produto produto = produtoDAO.pesquisarId(idProduto);
                if (produto != null) {
                    System.out.print("Quantidade do Produto: ");
                    int quantidade = scanner.nextInt();
                    produto.setEstoque(quantidade);
                    produtos.add(produto);
                } else {
                    System.out.println("Produto não encontrado.");
                }
                System.out.print("Deseja adicionar outro produto? (s/n): ");
                adicionarProduto = scanner.next().charAt(0);
                scanner.nextLine(); // Consumir o "\n"
            } while (adicionarProduto == 's');

            pedido.setDataPedido(data);
            pedido.setStatus(status);
            pedido.setPercentualDesconto(desconto);
            pedido.setCliente(cliente);
            pedido.setProdutos(produtos);

            pedidoDAO.alterar(pedido);
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    private static void excluirPedido() {
        System.out.println("\n=== Excluir Pedido ===");
        System.out.print("ID do Pedido: ");
        int id = scanner.nextInt();
        pedidoDAO.excluir(id);
    }

    private static void listarPedidos() {
        System.out.println("\n=== Listar Todos os Pedidos ===");
        List<Pedido> pedidos = pedidoDAO.listarTodos();
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }


}
