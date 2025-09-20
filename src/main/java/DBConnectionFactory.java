import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DBConnectionFactory {

    // MySQL Connection Details
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/loja";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "password"; // Placeholder

    // MongoDB Connection Details
    private static final String MONGODB_CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String MONGODB_DATABASE_NAME = "loja";

    public static Connection getMySQLConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
            System.out.println("Conectado ao MySQL com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro de Conexão com MySQL: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    public static MongoDatabase getMongoDBDatabase() {
        try {
            MongoClient mongoClient = MongoClients.create(MONGODB_CONNECTION_STRING);
            MongoDatabase database = mongoClient.getDatabase(MONGODB_DATABASE_NAME);
            System.out.println("Conectado ao MongoDB com sucesso!");
            return database;
        } catch (Exception e) {
            System.err.println("Erro de Conexão com MongoDB: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão MySQL: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void closeMongoClient(MongoClient mongoClient) {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
