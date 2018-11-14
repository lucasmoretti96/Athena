package br.com.olimpia.athena;

import static Interface.IController.hal;
import static Interface.IController.os;
import br.com.olimpia.athena.handler.Computer;
import Statements.MachinesRepository;
import Statements.RealTimeMachinesRepository;
import br.com.olimpia.athena.handler.RealTimeComputer;
import java.sql.SQLException;
import java.util.Arrays;

import oshi.util.FormatUtil;

public class Main{
    public static void main(String[] args) throws InterruptedException, SQLException {
        Computer info = new Computer();
        RealTimeComputer info2 = new RealTimeComputer();
        MachinesRepository repository = new MachinesRepository();
        RealTimeMachinesRepository repository2 = new RealTimeMachinesRepository();
        System.out.println("----Processador-------");
        System.out.println("Marca: "+info.getCpuNameOshi());
        System.out.println("Temperatura: "+info2.getCpuTemperatureOshi());
        System.out.print(String.format("Uso da CPU: %.2f",info2.getCpuUsagePorcentageOshi()));
        System.out.println("%");
        System.out.println("----Memória RAM-----");
        System.out.println("Memória RAM Total: "+info.getRamTotalOshi());
        System.out.println("Memória RAM Usada: "+FormatUtil.formatBytesDecimal(info2.getRamUsageOshi()));
        System.out.println("Memória RAM Disponível: "+FormatUtil.formatBytesDecimal(info2.getRamAvailableOshi()));
        System.out.println("----HD------------");
        System.out.println("Memória Total do HD: "+info.getHDTotalOshi());
        System.out.println("Informações Gerais HD: ");
        System.out.println(Arrays.toString(info2.getHDFilesStoresSizesOshi()));
        System.out.println("----Sistema Operacional----------");
        System.out.println("Tempo de Uso da Máquina: "+FormatUtil.formatElapsedSecs(info2.getComputerUsageTimeOshi()));
        System.out.println("-------Rede-----------------------");
        System.out.println("IP da máquina: "+info.getComputerIpAddressOshi());
        System.out.println("--------Processos------------------");
        System.out.println("Processos sendo executados na máquina: ");
        System.out.println(info2.getComputerProcessesOshi(os, hal.getMemory()));
        
        Computer machine1= info.getComputerActual();
        boolean insertresponse = repository.insertComputerActualStaticData(machine1);
        System.out.println(insertresponse);
        RealTimeComputer machine2 = info2.getRealTimeComputer();
        repository2.insertComputerActualData(machine2);
    }
}
