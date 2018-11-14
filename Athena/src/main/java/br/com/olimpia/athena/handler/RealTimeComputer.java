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

public class RealTimeComputer implements Interface.IController{
    private long ramUsage;
    private long ramAvailable;
    private long computerUsageTime;
    private OSFileStore[] hdsDetails;
    private double cpuTemperature;
    private double cpuUsagePorcentage;
    
    public RealTimeComputer(){
    }
    
    public RealTimeComputer(
            long computerUsageTime, 
            long ramUsage, 
            long ramAvailable, 
            double cpuUsagePorcentage, 
            double cpuTemperature,
            OSFileStore[] hdsDetails){
        this.computerUsageTime = computerUsageTime;
        this.ramUsage = ramUsage;
        this.ramAvailable = ramAvailable;
        this.cpuUsagePorcentage = cpuUsagePorcentage;
        this.cpuTemperature = cpuTemperature;
        this.hdsDetails = hdsDetails;
    }
    public RealTimeComputer getRealTimeComputer(){
        return new RealTimeComputer(
                getComputerUsageTimeOshi(),
                getRamUsageOshi(),
                getRamAvailableOshi(),
                getCpuUsagePorcentageOshi(),
                getCpuTemperatureOshi(),
                getHDFilesStoresSizesOshi());
    }
        public double getCpuUsagePorcentage() {
        return cpuUsagePorcentage;
    }

    public void setCpuUsagePorcentage(double cpuUsagePorcentage) {
        this.cpuUsagePorcentage = cpuUsagePorcentage;
    }
        public double getCpuTemperature() {
        return cpuTemperature;
    }

    public void setCpuTemperature(double cpuTemperature) {
        this.cpuTemperature = cpuTemperature;
    }
        public long getRamUsage() {
        return ramUsage;
    }

    public void setRamUsage(long ramUsage) {
        this.ramUsage = ramUsage;
    }
    public long getRamAvailable() {
        return ramAvailable;
    }
    public long getComputerUsageTime() {
        return computerUsageTime;
    }

    public void setComputerUsageTime(long computerUsageTime) {
        this.computerUsageTime = computerUsageTime;
    }
        public OSFileStore[] getHdsDetails() {
        return hdsDetails;
    }

    public void setHdsDetails(OSFileStore[] hdsDetails) {
        this.hdsDetails = hdsDetails;
    }
    public void setRamAvailable(long ramAvailable) {
        this.ramAvailable = ramAvailable;
    }
     public long getComputerUsageTimeOshi() {
        computerUsageTime = hal.getProcessor().getSystemUptime();
        return computerUsageTime;
    }
     public long getRamUsageOshi() {
        ramUsage = (hal.getMemory().getTotal() - hal.getMemory().getAvailable());
        return ramUsage;
    }
    public long getRamAvailableOshi() {
        ramAvailable = hal.getMemory().getAvailable();
        return ramAvailable;
    }
    public double getCpuUsagePorcentageOshi() {
        cpuUsagePorcentage = hal.getProcessor().getSystemCpuLoadBetweenTicks();
        cpuUsagePorcentage = cpuUsagePorcentage * 100;

        return cpuUsagePorcentage;
    }
    public double getCpuTemperatureOshi() {
        cpuTemperature = si.getHardware().getSensors().getCpuTemperature();
        return cpuTemperature;
    }
        public OSFileStore[] getHDFilesStoresSizesOshi() {
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
       public List<OSProcess> getComputerProcessesOshi(OperatingSystem os, GlobalMemory memory) {
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
        return procs;
    }
}
