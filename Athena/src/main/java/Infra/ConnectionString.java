package Infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionString {
    private final String connectionString = "jdbc:sqlserver://athenaproject.database.windows.net:1433;database=teste3;user=master@athenaproject;password=Athena_coruja;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    
    public Connection createConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectionString);
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionString.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
