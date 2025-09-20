import java.util.List;

public interface ProdutoRepository {
    List<Produto> listaProdutos();
    void insereProduto(Produto produto);
    void alteraValorProduto(String idProduto, String valor);
    void apagaProduto(String idProduto);
}
