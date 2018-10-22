package br.com.olimpia.athena;

import br.com.olimpia.athena.handler.Computer;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Computer info = new Computer();
        System.out.println(info.getCpuName()+"\n"+info.getCpuUsage()+"\n"+info.getRamTotal()+
                "\n"+info.getRamUsage()+"\n"+info.getRamAvailable()+"\n"+info.getHDTotal());
    }
}
