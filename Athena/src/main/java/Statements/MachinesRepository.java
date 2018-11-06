package Statements;

import Infra.ConnectionString;
import br.com.olimpia.athena.handler.Computer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MachinesRepository {

    public Connection MachinesRepository() {
     return new ConnectionString().createConnection();
    }
    
    public boolean insertComputerActualStaticData(Computer computer){
        try{
        PreparedStatement query = MachinesRepository().prepareStatement("insert into Machines (CpuName,HdTotal,IP,RamTotal) values(?,?,?,?)");
        query.setString(1, computer.getCpuName());
        query.setString(2, computer.getHdTotal());
        query.setString(3, computer.getComputerIpAddress());
        query.setString(4, computer.getRamTotal());
        query.execute();
        disconnect();
        return true;
        }catch(SQLException ex){
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void disconnect() {
        try {
            MachinesRepository().close();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
