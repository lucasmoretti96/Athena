package br.com.olimpia.athena;

import static Interface.IController.hal;
import static Interface.IController.os;
import br.com.olimpia.athena.handler.Computer;
import Statements.MachinesRepository;
import java.util.Arrays;

import oshi.util.FormatUtil;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        Computer info = new Computer();
        MachinesRepository repository = new MachinesRepository();
        System.out.println("----Processador-------");
        System.out.println("Marca: "+info.getCpuNameOshi());
        System.out.println("Temperatura: "+info.getCpuTemperatureOshi());
        System.out.print(String.format("Uso da CPU: %.2f",info.getCpuUsagePorcentageOshi()));
        System.out.println("%");
        System.out.println("----Memória RAM-----");
        System.out.println("Memória RAM Total: "+info.getRamTotalOshi());
        System.out.println("Memória RAM Usada: "+FormatUtil.formatBytesDecimal(info.getRamUsageOshi()));
        System.out.println("Memória RAM Disponível: "+FormatUtil.formatBytesDecimal(info.getRamAvailableOshi()));
        System.out.println("----HD------------");
        System.out.println("Memória Total do HD: "+info.getHDTotalOshi());
        System.out.println("Informações Gerais HD: ");
        System.out.println(Arrays.toString(info.getHDFilesStoresSizesOshi()));
        System.out.println("----Sistema Operacional----------");
        System.out.println("Tempo de Uso da Máquina: "+FormatUtil.formatElapsedSecs(info.getComputerUsageTimeOshi()));
        System.out.println("-------Rede-----------------------");
        System.out.println("IP da máquina: "+info.getComputerIpAddressOshi());
        System.out.println("--------Processos------------------");
        System.out.println("Processos sendo executados na máquina: ");
        System.out.println(info.getComputerProcessesOshi(os, hal.getMemory()));
        
        Computer machine1= info.getComputerActual();
        boolean insertresponse = repository.insertComputerActualStaticData(machine1);
        System.out.println(insertresponse);
    }
}
