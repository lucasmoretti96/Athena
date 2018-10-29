package br.com.olimpia.athena.handler;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;

public class Computer implements Interface.IController {

    private double cpuUsagePorcentage;
    private String cpuName;
    private String computerIpAddress;
    private double cpuTemperature;
    private long ramTotal;
    private long ramUsage;
    private long ramAvailable;
    private long hdTotal;
    private long computerUsageTime;
    private OSFileStore[] hdsDetails;

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
    
    public String getComputerIpAddress(){
        computerIpAddress = si.getOperatingSystem().getNetworkParams().getIpv4DefaultGateway();
        return computerIpAddress;
    }
    
    public OSFileStore[] getHDFilesStoresSizes(){
        hdsDetails = os.getFileSystem().getFileStores();
        
        for (OSFileStore fs : hdsDetails) {
            long usable = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            System.out.format(" %s (%s) [%s] %s of %s free (%.1f%%) \n",

                    fs.getName(), fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),

                    FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total);
    }
        return hdsDetails;
}
    public List<OSProcess> printProcesses(OperatingSystem os, GlobalMemory memory) {
        OSProcess p = new OSProcess();

        System.out.println("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());

        List<OSProcess> procs = Arrays.asList(os.getProcesses(5, ProcessSort.CPU));

        System.out.println("   PID  %CPU %MEM       VSZ       RSS Name");

        for (int i = 0; i < procs.size() && i < 5; i++) {

            p = procs.get(i);

            System.out.format(" %5d %5.1f %4.1f %9s %9s %s%n", p.getProcessID(),

                    100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),

                    100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),

                    FormatUtil.formatBytes(p.getResidentSetSize()), p.getName());
        }
        return (List<OSProcess>) p;
    }
}
