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

public class Computer {

    private double cpuUsage;
    private String cpuName;
    private long ramTotal;
    private long ramUsage;
    private long ramAvailable;
    private long hdTotal;
    private long hdUsage;
    
    SystemInfo si = new SystemInfo();
    OperatingSystem os = si.getOperatingSystem();
    HardwareAbstractionLayer hal = si.getHardware();
    
    public double getCpuUsage() {
        cpuUsage = hal.getProcessor().getSystemCpuLoadBetweenTicks();
        return cpuUsage;
    }
    
    public String getCpuName() {
        cpuName = hal.getProcessor().getName();
        return cpuName;
    }
    
    public long getRamTotal() {
        ramTotal = hal.getMemory().getTotal();
        return ramTotal;
    }
    
    public long getRamUsage() {
        ramUsage = hal.getMemory().getSwapUsed();
        return ramUsage;
    }
    
    public long getRamAvailable() {
        ramAvailable = hal.getMemory().getAvailable();
        return ramAvailable;
    }
    
    public long getHDTotal() {
        hdTotal = si.getOperatingSystem().getProcess(0).getBytesRead();
        return hdTotal;
    }
    
    public long getHDUsage() {
        hdUsage = si.getOperatingSystem().getProcess(0).getBytesRead();
        return hdUsage;
    }
    
    public void getHD() {
        List<Long> hd = new ArrayList<>();
        HWDiskStore[] disks = hal.getDiskStores();
        for (HWDiskStore disk : disks) {
            hd.add(disk.getSize());
        }
    }
}
