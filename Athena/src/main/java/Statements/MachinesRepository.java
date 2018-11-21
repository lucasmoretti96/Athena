package Statements;

import Infra.ConnectionString;
import br.com.olimpia.athena.handler.Computer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MachinesRepository {
    
    public boolean insertComputerActualStaticData(Computer computer, int idMachine){
        try{
        Connection conn = new ConnectionString().createConnection();
        PreparedStatement query = conn.prepareStatement("insert into Machines (idMachines, CpuName,HdTotal,IP,RamTotal) values(?,?,?,?,?)");
        query.setInt(1, idMachine);
        query.setString(2, computer.getCpuName());
        query.setString(3, computer.getHdTotal());
        query.setString(4, computer.getComputerIpAddress());
        query.setString(5, computer.getRamTotal());
        query.execute();
        disconnect(conn);
        return true;
        }catch(SQLException ex){
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void disconnect(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
