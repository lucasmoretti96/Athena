package Statements;

import Infra.ConnectionString;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MachinesRepository {

    private Connection connection;

    public void MachinesRepository() {
        connection = new ConnectionString().createConnection();
    }

    public void insertCpuName(String cpuName) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("insert into tbMaquinas (Processador) values (%s)" + cpuName);
            prepareStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertRamTotal(long ramTotal) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("insert into tbMaquinas (MemoriaRAM) values (%x)" + ramTotal);
            prepareStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertComputerIpAdress(String computerIpAdress) {

        try {
            PreparedStatement prepareStatement = connection.prepareStatement("insert into tbMaquinas (IP) values (%s)" + computerIpAdress);
            prepareStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void insertHdTotal (String hdTotal){
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("insert into tbMaquinas (DiscoRigido) values (%s)" + hdTotal);
            prepareStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
