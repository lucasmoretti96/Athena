package Statements;

import Infra.ConnectionString;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MachinesRepository {
    
    private Connection conexao;

    public MachinesRepository() {
        conexao = new ConnectionString().criarConexao();
    }
    
    public void inserirRegistro(String talcoisa) {
        try {
            PreparedStatement prepareStatement = conexao.prepareStatement("insert bla ala");
            prepareStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void desconectar() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
