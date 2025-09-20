import java.util.List;
import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoRepository repository = null;

        System.out.println("Escolha o tipo de repositório:");
        System.out.println("1. Em Memória");
        System.out.println("2. MySQL");
        System.out.println("3. MongoDB");
        System.out.print("Opção: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                repository = new ProdutoRepositoryInMemory();
                System.out.println("Repositório Em Memória selecionado.");
                break;
            case 2:
                repository = new ProdutoRepositoryMySQL();
                System.out.println("Repositório MySQL selecionado.");
                break;
            case 3:
                repository = new ProdutoRepositoryMongoDB();
                System.out.println("Repositório MongoDB selecionado.");
                break;
            default:
                System.out.println("Opção inválida. Usando repositório Em Memória por padrão.");
                repository = new ProdutoRepositoryInMemory();
                break;
        }

        if (repository == null) {
            System.err.println("Erro: Repositório não inicializado. Saindo.");
            return;
        }

        // CRUD operations

        // Listar produtos
        System.out.println("\n--- Lista Original de Produtos ---");
        List<Produto> produtos = repository.listaProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }

        // Insere novo produto
        Produto newProduct = new Produto("7", "Prod7", "Bla Bla", "500.0", "Bla Bla");
        repository.insereProduto(newProduct);
        System.out.println("\n--- Lista com Novo Produto ---");
        repository.listaProdutos().forEach(System.out::println);

        // Listar produtos
        System.out.println("\n--- Lista de Produtos com novo item ---");
        List<Produto> produtos = repository.listaProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }

        // Altera valor do produto
        repository.alteraValorProduto("7", "400");
        System.out.println("\n--- Lista com Valor do Produto Alterado ---");
        repository.listaProdutos().forEach(System.out::println);


        // Listar produtos
        System.out.println("\n--- Lista de Produtos com novo item atualizado ---");
        List<Produto> produtos = repository.listaProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }

        // Apaga um produto
        repository.apagaProduto("7");
        System.out.println("\n--- Lista com Produto Apagado ---");
        repository.listaProdutos().forEach(System.out::println);

        // Teste de produto não encontrado
        // System.out.println("\n--- Testando operações com produto não existente ---");
        // repository.alteraValorProduto("99", "100");
        // repository.apagaProduto("99");

        // Listar produtos
        System.out.println("\n--- Lista de Produtos final ---");

        List<Produto> produtos = repository.listaProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            produtos.forEach(System.out::println);
        }

        scanner.close();
    }
}
