package Statements;

import Infra.ConnectionString;
import java.sql.Connection;
import java.sql.PreparedStatement;
import br.com.olimpia.athena.handler.RealTimeComputer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RealTimeMachinesRepository {
    
    Connection conn = new ConnectionString().createConnection();
    
    public void insertComputerActualData(RealTimeComputer realTimeComputer, int idMachine) throws SQLException, InterruptedException{
        try{    
                PreparedStatement query = conn.prepareStatement("insert into CpuInf (idMachines, CpuUsage, CpuTemperature) values(?,?,?)");
                query.setInt(1, idMachine);
                query.setDouble(2, realTimeComputer.getCpuUsagePorcentage());
                query.setDouble(3,realTimeComputer.getCpuTemperature());
                query.execute();
                
                PreparedStatement query2 = conn.prepareStatement("insert into RamMemoryInf (idMachines, RamUsage, RamAvailable) values(?,?,?)");
                query2.setInt(1, idMachine);
                query2.setLong(2, realTimeComputer.getRamUsage());
                query2.setLong(3, realTimeComputer.getRamAvailable());
                query2.execute();
                
                PreparedStatement query3 = conn.prepareStatement("insert into TIMEUSAGEINF (idMachines, TimeUsage) values(?,?)");
                query3.setInt(1, idMachine);
                query3.setLong(2, realTimeComputer.getComputerUsageTime());
                query3.execute();
                
                PreparedStatement query4 = conn.prepareStatement("insert into HardDiskInf (idMachine, HdTotal, HdUsage) values(?,?,?)");
                query4.setInt(1, idMachine);
                query4.setLong(2, realTimeComputer.getHdTotal());
                query4.setLong(3, realTimeComputer.getHdUsage());
                query4.execute();                
        }catch(SQLException ex){
            disconnect(conn);
            Logger.getLogger(RealTimeMachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        disconnect(conn);
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
