package Infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionString {
    private final String connectionString = "";
    private String commandSQL;
    
public String Connection(String typeStatement) {
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            this.commandSQL = typeStatement;
            String detectType = typeStatement.substring(0, 6).toUpperCase();
            if (null == detectType) {
                conn.close();
                return "Comando SQL não identificado, conexão com o banco fechada.";
            } else {
                switch (detectType) {
                    case "SELECT":
                        SelectCommand(conn);
                        break;
                    case "INSERT":
                        InsertCommand(conn);
                        break;
                    default:
                        conn.close();
                        return "Comando SQL não identificado, conexão com o banco fechada.";
                }
            }
            conn.close();
        } catch (SQLException e) {
            return "Falha ao conectar com o banco\n" + e.getErrorCode();
        }
        return "Comando realizado com sucesso.";
    }

    private void SelectCommand(Connection conn) {
        try (Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(this.commandSQL)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }

    private void InsertCommand(Connection conn) {
        try (Statement statement = conn.createStatement()) {
            int rowsAffected = statement.executeUpdate(this.commandSQL);
            System.out.println(rowsAffected + " linha(s) inseridas");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
    }
}
