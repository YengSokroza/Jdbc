import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Product {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/Product";
        String username = "postgres";
        String password = "roza2023";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection Success!");

            Statement statement = connection.createStatement();
            String sql = "SELECT id, name, price_per_unit, active_for_sell FROM product";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float pricePerUnit = resultSet.getFloat("price_per_unit");
                boolean activeForSell = resultSet.getBoolean("active_for_sell");

                System.out.println("ProductID: " + id);
                System.out.println("ProductName: " + name);
                System.out.println("Price per Unit: " + pricePerUnit);
                System.out.println("Active for Sell: " + activeForSell);
                System.out.println("-------------");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error connecting to PostgreSQL: " + e.getMessage());
        }
    }
}
