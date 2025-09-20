import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoRepositoryInMemory implements ProdutoRepository {
    private Map<String, Produto> produtos;

    public ProdutoRepositoryInMemory() {
        this.produtos = new HashMap<>();
        // Add some initial data for testing
        produtos.put("1", new Produto("1", "Prod1", "Desc1", "100.0", "Ativo"));
        produtos.put("2", new Produto("2", "Prod2", "Desc2", "200.0", "Ativo"));
        produtos.put("3", new Produto("3", "Prod3", "Desc3", "300.0", "Inativo"));
    }

    public List<Produto> listaProdutos() {
        return new ArrayList<>(produtos.values());
    }

    public void insereProduto(Produto produto) {
        produtos.put(produto.getIdProduto(), produto);
    }

    public void alteraValorProduto(String idProduto, String valor) {
        Produto produto = produtos.get(idProduto);
        if (produto != null) {
            produto.setValor(valor);
        } else {
            System.out.println("\nProduto com ID " + idProduto + " não encontrado.");
        }
    }

    public void apagaProduto(String idProduto) {
        if (produtos.remove(idProduto) == null) {
            System.out.println("\nProduto com ID " + idProduto + " não encontrado.");
        }
    }
}
