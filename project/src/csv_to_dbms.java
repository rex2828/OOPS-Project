import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import static java.lang.Integer.parseInt;

public class csv_to_dbms {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/project";
        String username = "root";
        String password = "password";

        String filePath = "csv-files/merged_officercsv.csv";

        int batchSize = 100;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);

            String sql = "call Insertion(?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            int count = 0;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");

                String id = data[0];
                String name = data[1];
                String designation = data[2];
                String rank = data[3];
                String marital_status = data[4];
                statement.setInt(1, parseInt(id));
                statement.setString(2, name);
                statement.setString(3, designation);
                statement.setString(4, rank);
                statement.setString(5, marital_status);
                statement.addBatch();
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();
            System.out.println("Data has been inserted successfully.");

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}