package energister.jdbc.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class App {
    public static void main(String[] args) throws SQLException {
        String connectionString = getEnvVariable("CONNECTION_STRING");
        String connectionUrl = connectionString
                // use Windows Integrated Authentication
                + ";integratedSecurity=true"
                // workaround for javax.net.ssl.SSLHandshakeException
                + ";encrypt=true"
                + ";trustServerCertificate=true"
                + ";loginTimeout=5";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT 1");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    System.out.println(resultSet.getString(i));
                }
            }
        }
    }

    private static String getEnvVariable(String name) {
        return Optional.ofNullable(System.getenv(name))
                .orElseThrow(() -> new IllegalStateException(name + " system variable is not defined"));
    }
}
