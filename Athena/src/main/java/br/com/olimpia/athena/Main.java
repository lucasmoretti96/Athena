package br.com.olimpia.athena;

import Screens.LoginAdmin;
import Screens.SelectArea;
import br.com.olimpia.athena.handler.Computer;
import Statements.MachinesRepository;
import Statements.RealTimeMachinesRepository;
import br.com.olimpia.athena.handler.RealTimeComputer;
import java.sql.SQLException;
import java.util.Arrays;

import oshi.util.FormatUtil;

public class Main{
    public static void main(String[] args) throws InterruptedException, SQLException {
        
        int idMachine = new SelectArea().getIdMachine();
        Computer info = new Computer();
        MachinesRepository repository = new MachinesRepository();
        Computer machine1= info.getComputerActual();
        boolean insertresponse = repository.insertComputerActualStaticData(machine1, idMachine);
        System.out.println(insertresponse);
        
        while(true){
        RealTimeComputer info2 = new RealTimeComputer();
        RealTimeMachinesRepository repository2 = new RealTimeMachinesRepository();
       
        RealTimeComputer machine2 = info2.getRealTimeComputer();
        repository2.insertComputerActualData(machine2, idMachine);
        Thread.sleep(5000);
        }
    }
}
