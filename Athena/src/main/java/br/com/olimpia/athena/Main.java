package br.com.olimpia.athena;

import static Interface.IController.hal;
import static Interface.IController.os;
import br.com.olimpia.athena.handler.Computer;
import java.util.Arrays;

import oshi.util.FormatUtil;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        Computer info = new Computer();
        System.out.println("----Processador-------");
        System.out.println("Marca: "+info.getCpuName());
        System.out.println("Temperatura: "+info.getCpuTemperature());
        System.out.print(String.format("Uso da CPU: %.2f",info.getCpuUsagePorcentage()));
        System.out.println("%");
        System.out.println("----Memória RAM-----");
        System.out.println("Memória RAM Total: "+FormatUtil.formatBytesDecimal(info.getRamTotal()));
        System.out.println("Memória RAM Usada: "+FormatUtil.formatBytesDecimal(info.getRamUsage()));
        System.out.println("Memória RAM Disponível: "+FormatUtil.formatBytesDecimal(info.getRamAvailable()));
        System.out.println("----HD------------");
        System.out.println("Memória Total do HD: "+FormatUtil.formatBytesDecimal(info.getHDTotal()));
        System.out.println("Informações Gerais HD: ");
        System.out.println(Arrays.toString(info.getHDFilesStoresSizes()));
        System.out.println("----Sistema Operacional----------");
        System.out.println("Tempo de Uso da Máquina: "+FormatUtil.formatElapsedSecs(info.getComputerUsageTime()));
        System.out.println("-------Rede-----------------------");
        System.out.println("IP da máquina: "+info.getComputerIpAddress());
        System.out.println("--------Processos------------------");
        System.out.println("Processos sendo executados na máquina: ");
        System.out.println(info.printProcesses(os, hal.getMemory()));
    }
}
