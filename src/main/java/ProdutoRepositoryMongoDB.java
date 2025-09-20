import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryMongoDB implements ProdutoRepository {

    private MongoCollection<Document> collection;

    public ProdutoRepositoryMongoDB() {
        MongoDatabase database = DBConnectionFactory.getMongoDBDatabase();
        if (database != null) {
            this.collection = database.getCollection("produtos"); // Collection name
            initializeData();
        } else {
            System.err.println("Não foi possível conectar ao MongoDB. Repositório não inicializado.");
        }
    }

    private void initializeData() {
        if (collection != null && collection.countDocuments() == 0) {
            List<Document> initialProducts = new ArrayList<>();
            initialProducts.add(new Document("idProduto", "1").append("nome", "Prod1").append("descricao", "Desc1").append("valor", "100.0").append("estado", "Ativo"));
            initialProducts.add(new Document("idProduto", "2").append("nome", "Prod2").append("descricao", "Desc2").append("valor", "200.0").append("estado", "Ativo"));
            initialProducts.add(new Document("idProduto", "3").append("nome", "Prod3").append("descricao", "Desc3").append("valor", "300.0").append("estado", "Inativo"));
            collection.insertMany(initialProducts);
            System.out.println("Dados iniciais inseridos no MongoDB.");
        }
    }

    @Override
    public List<Produto> listaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        if (collection != null) {
            for (Document doc : collection.find()) {
                produtos.add(documentToProduto(doc));
            }
        }
        return produtos;
    }

    @Override
    public void insereProduto(Produto produto) {
        if (collection != null) {
            Document doc = produtoToDocument(produto);
            collection.insertOne(doc);
            System.out.println("Produto " + produto.getNome() + " inserido no MongoDB.");
        }
    }

    @Override
    public void alteraValorProduto(String idProduto, String valor) {
        if (collection != null) {
            collection.updateOne(Filters.eq("idProduto", idProduto), Updates.set("valor", valor));
            System.out.println("Valor do produto " + idProduto + " alterado para " + valor + " no MongoDB.");
        }
    }

    @Override
    public void apagaProduto(String idProduto) {
        if (collection != null) {
            collection.deleteOne(Filters.eq("idProduto", idProduto));
            System.out.println("Produto " + idProduto + " apagado do MongoDB.");
        }
    }

    private Document produtoToDocument(Produto produto) {
        return new Document("idProduto", produto.getIdProduto())
                .append("nome", produto.getNome())
                .append("descricao", produto.getDescricao())
                .append("valor", produto.getValor())
                .append("estado", produto.getEstado());
    }

    private Produto documentToProduto(Document doc) {
        return new Produto(
                doc.getString("idProduto"),
                doc.getString("nome"),
                doc.getString("descricao"),
                doc.getString("valor"),
                doc.getString("estado")
        );
    }
}
