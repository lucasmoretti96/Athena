package br.com.olimpia.athena.handler;

import java.util.Arrays;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import oshi.hardware.HWDiskStore;

public class Computer implements Interface.IController {

    private double cpuUsagePorcentage;
    private String cpuName;
    private double cpuTemperature;
    private long ramTotal;
    private long ramUsage;
    private long ramAvailable;
    private long hdTotal;
    private long computerUsageTime;

    public double getCpuUsagePorcentage() {
        cpuUsagePorcentage = hal.getProcessor().getSystemCpuLoadBetweenTicks();
        cpuUsagePorcentage = cpuUsagePorcentage*100;
        
        return cpuUsagePorcentage;
    }

    public String getCpuName() {
        cpuName = hal.getProcessor().getName();
        return cpuName;
    }

    public double getCpuTemperature() {
        cpuTemperature = si.getHardware().getSensors().getCpuTemperature();
        return cpuTemperature;
    }

    public long getRamTotal() {
        ramTotal = hal.getMemory().getTotal();
        return ramTotal;
    }

    public long getRamUsage() {
        ramUsage = (hal.getMemory().getTotal() - hal.getMemory().getAvailable());
        return ramUsage;
    }

    public long getRamAvailable() {
        ramAvailable = hal.getMemory().getAvailable();
        return ramAvailable;
    }

    public long getHDTotal() {
        
        List<Long> hds = new ArrayList<>();
        
        HWDiskStore[] disks = hal.getDiskStores();
        for (HWDiskStore disk : disks) {
           hds.add(disk.getSize());
        }
        for (int i = 0; i < hds.size(); i++) {
            hdTotal = (hdTotal + hds.get(i));
        }
        return hdTotal;
    }
    
    public long getComputerUsageTime(){
        computerUsageTime = hal.getProcessor().getSystemUptime();
        return computerUsageTime;
    }
}
