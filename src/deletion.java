import java.sql.*;

public class deletion {
    public static void main(String[] args) {
        if (args[1] == "id") {
            String jdbcUrl = "jdbc:mysql://localhost:3306/project";
            String username = "root";
            String password = "root";
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(jdbcUrl, username, password);

                String sql = "call Deletion(" + args[2] + ")";
                Statement stmt = connection.createStatement();
                stmt.executeQuery(sql);
                System.out.println("Officer with id = " + args[2] + " deleted.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println("Give correct arguments for deletion.");
        }
    }
}
