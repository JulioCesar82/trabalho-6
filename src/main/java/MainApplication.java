import java.util.List;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoRepository repository = null;

        System.out.println("\nEscolha o tipo de repositório:");
        System.out.println("1. Em Memória");
        System.out.println("2. MySQL");
        System.out.println("3. MongoDB");
        System.out.print("Opção: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                repository = new ProdutoRepositoryInMemory();
                System.out.println("\nRepositório Em Memória selecionado.");
                break;
            case 2:
                repository = new ProdutoRepositoryMySQL();
                System.out.println("\nRepositório MySQL selecionado.");
                break;
            case 3:
                repository = new ProdutoRepositoryMongoDB();
                System.out.println("\nRepositório MongoDB selecionado.");
                break;
            default:
                System.out.println("\nOpção inválida. Usando repositório Em Memória por padrão.");
                repository = new ProdutoRepositoryInMemory();
                break;
        }

        if (repository == null) {
            System.err.println("\nErro: Repositório não inicializado. Saindo.");
            return;
        }

        // CRUD operations
        List<Produto> produtos;

        // Listar produtos
        System.out.println("\n--- Lista Original de Produtos ---");
        produtos = repository.listaProdutos();
        if (produtos.isEmpty()) {
            System.out.println("\nNenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }

        // Insere novo produto
        Produto newProduct = new Produto("7", "Prod7", "Bla Bla", "500.0", "Bla Bla");
        System.out.println("\n--- Inserindo Novo Produto ---");
        repository.insereProduto(newProduct);
        
        // Listar produtos
        System.out.println("\n--- Lista de Produtos com novo item ---");
        produtos = repository.listaProdutos();
        if (produtos.isEmpty()) {
            System.out.println("\nNenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }

        // Altera valor do produto
        System.out.println("\n--- Alterando Valor do Produto ---");
        repository.alteraValorProduto("7", "400");        

        // Listar produtos
        System.out.println("\n--- Lista de Produtos com novo item atualizado ---");
        produtos = repository.listaProdutos();
        if (produtos.isEmpty()) {
            System.out.println("\nNenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }

        // Apaga um produto
        System.out.println("\n--- Apagando Produto ---");
        repository.apagaProduto("7");
        
        // Teste de produto não encontrado
        // System.out.println("\n--- Testando operações com produto não existente ---");
        // repository.alteraValorProduto("99", "100");
        // repository.apagaProduto("99");

        // Listar produtos
        System.out.println("\n--- Lista de Produtos final ---");

        produtos = repository.listaProdutos();
        if (produtos.isEmpty()) {
            System.out.println("\nNenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }


        System.out.println("\n--- Encerrando Aplicação ---");

        scanner.close();
    }
}
