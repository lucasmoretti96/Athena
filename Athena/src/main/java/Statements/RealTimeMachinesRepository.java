package Statements;

import Infra.ConnectionString;
import java.sql.Connection;
import java.sql.PreparedStatement;
import br.com.olimpia.athena.handler.RealTimeComputer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RealTimeMachinesRepository {
    
    public Connection RealTimeMachinesRepository(){
        return new ConnectionString().createConnection();
    }
    
    public void insertComputerActualData(RealTimeComputer realTimeComputer) throws SQLException, InterruptedException{
        try{
            while(true){
                PreparedStatement query = RealTimeMachinesRepository().prepareStatement("insert into xxx (cpuUsagePorcentage, cpuTemperature, ramUsage, ramAvailable, computerUsageTime, hdsDetails) values(?,?,?,?,?,?)");
                query.setDouble(1, realTimeComputer.getCpuUsagePorcentage());
                query.setDouble(2,realTimeComputer.getCpuTemperature());
                query.setLong(3, realTimeComputer.getRamUsage());
                query.setLong(4, realTimeComputer.getRamAvailable());
                query.setLong(5, realTimeComputer.getComputerUsageTime());
                //query.setXXX(6, computer.getHdsDetails());
                query.execute();
                Thread.sleep(3000);
            }
        }catch(SQLException ex){
            disconnect();
            Logger.getLogger(RealTimeMachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void disconnect() {
        try {
            RealTimeMachinesRepository().close();
        } catch (SQLException ex) {
            Logger.getLogger(MachinesRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
