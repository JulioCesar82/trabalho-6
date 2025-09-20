import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositoryMySQL implements ProdutoRepository {

    public ProdutoRepositoryMySQL() {
        initializeData();
    }

    private void initializeData() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionFactory.getMySQLConnection();
            if (conn != null) {
                stmt = conn.createStatement();

                // Create table if it doesn't exist
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Produto (" +
                                        "idProduto VARCHAR(255) PRIMARY KEY," +
                                        "nome VARCHAR(255)," +
                                        "descricao VARCHAR(255)," +
                                        "valor VARCHAR(255)," +
                                        "estado VARCHAR(255)" +
                                        ")";
                stmt.executeUpdate(createTableSQL);

                // Check if table is empty
                rs = stmt.executeQuery("SELECT COUNT(*) FROM Produto");
                if (rs.next() && rs.getInt(1) == 0) {
                    // Insert initial data
                    String insertSQL = "INSERT INTO Produto (idProduto, nome, descricao, valor, estado) VALUES " +
                                       "('1', 'Prod1', 'Desc1', '100.0', 'Ativo')," +
                                       "('2', 'Prod2', 'Desc2', '200.0', 'Ativo')," +
                                       "('3', 'Prod3', 'Desc3', '300.0', 'Inativo')";
                    stmt.executeUpdate(insertSQL);
                    System.out.println("\nDados iniciais inseridos no MySQL.");
                }
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao inicializar dados no MySQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                DBConnectionFactory.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Produto> listaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionFactory.getMySQLConnection();
            if (conn != null) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("select idProduto, nome, descricao, valor, estado from Produto");
                while (rs.next()) {
                    String idProduto = rs.getString("idProduto");
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    String valor = rs.getString("valor");
                    String estado = rs.getString("estado");
                    produtos.add(new Produto(idProduto, nome, descricao, valor, estado));
                }
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao listar produtos do MySQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                DBConnectionFactory.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return produtos;
    }

    @Override
    public void insereProduto(Produto produto) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBConnectionFactory.getMySQLConnection();
            if (conn != null) {
                stmt = conn.createStatement();
                String insere = "insert into Produto VALUES ('" + produto.getIdProduto() + "','"
                        + produto.getNome() + "','" + produto.getDescricao() + "','"
                        + produto.getValor() + "','" + produto.getEstado() + "')";
                stmt.executeUpdate(insere);
                System.out.println("\nProduto " + produto.getNome() + " inserido no MySQL.");
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao inserir produto no MySQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                DBConnectionFactory.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void alteraValorProduto(String idProduto, String valor) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBConnectionFactory.getMySQLConnection();
            if (conn != null) {
                stmt = conn.createStatement();
                String atualiza = "update Produto set valor = '" + valor + "' where idProduto = '" + idProduto + "'";
                stmt.executeUpdate(atualiza);
                System.out.println("\nValor do produto " + idProduto + " alterado para " + valor + " no MySQL.");
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao alterar valor do produto no MySQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                DBConnectionFactory.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void apagaProduto(String idProduto) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DBConnectionFactory.getMySQLConnection();
            if (conn != null) {
                stmt = conn.createStatement();
                String apaga = "delete from Produto where idProduto = '" + idProduto + "'";
                stmt.executeUpdate(apaga);
                System.out.println("\nProduto " + idProduto + " apagado do MySQL.");
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao apagar produto do MySQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                DBConnectionFactory.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
