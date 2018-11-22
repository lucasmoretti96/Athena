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
                PreparedStatement query = conn.prepareStatement("insert into CpuInf (CpuUsage, CpuTemperature, idMachines) values(?,?,?)");
                query.setDouble(1, realTimeComputer.getCpuUsagePorcentage());
                query.setDouble(2,realTimeComputer.getCpuTemperature());
                query.setInt(3, idMachine);
                query.execute();
                
                PreparedStatement query2 = conn.prepareStatement("insert into RamMemoryInf (RamUsage, RamAvailable, idMachines) values(?,?,?)");
                query2.setLong(1, realTimeComputer.getRamUsage());
                query2.setLong(2, realTimeComputer.getRamAvailable());
                query2.setInt(3, idMachine);
                query2.execute();
                
                PreparedStatement query3 = conn.prepareStatement("insert into TIMEUSAGEINF (TimeUsage, idMachines) values(?,?)");
                query3.setLong(1,realTimeComputer.getComputerUsageTime());
                query3.setInt(2, idMachine);
                query3.execute();
                
                PreparedStatement query4 = conn.prepareStatement("insert into HardDiskInf (HdTotal, HdUsage, idMachines) values(?,?,?)");
                query4.setLong(1, realTimeComputer.getHdTotal());
                query4.setLong(2, realTimeComputer.getHdUsage());
                query4.setInt(3, idMachine);
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
