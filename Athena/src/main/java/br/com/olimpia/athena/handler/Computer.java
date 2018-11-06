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
    private String ramTotal;
    private long ramUsage;
    private long ramAvailable;
    private String hdTotal;
    private long computerUsageTime;
    private OSFileStore[] hdsDetails;

    public Computer() {
    }

    public Computer(String cpuName, String computerIpAddress, String ramTotal, String hdTotal) {
        this.cpuName = cpuName;
        this.computerIpAddress = computerIpAddress;
        this.ramTotal = ramTotal;
        this.hdTotal = hdTotal;
    }
    public Computer getComputerActual(){
    return new Computer(
            getCpuNameOshi(), 
            getComputerIpAddressOshi(), 
            getRamTotalOshi(), 
            getHDTotalOshi());
    };

    public double getCpuUsagePorcentage() {
        return cpuUsagePorcentage;
    }

    public void setCpuUsagePorcentage(double cpuUsagePorcentage) {
        this.cpuUsagePorcentage = cpuUsagePorcentage;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getComputerIpAddress() {
        return computerIpAddress;
    }

    public void setComputerIpAddress(String computerIpAddress) {
        this.computerIpAddress = computerIpAddress;
    }

    public double getCpuTemperature() {
        return cpuTemperature;
    }

    public void setCpuTemperature(double cpuTemperature) {
        this.cpuTemperature = cpuTemperature;
    }

    public String getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(String ramTotal) {
        this.ramTotal = ramTotal;
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

    public void setRamAvailable(long ramAvailable) {
        this.ramAvailable = ramAvailable;
    }

    public String getHdTotal() {
        return hdTotal;
    }

    public void setHdTotal(String hdTotal) {
        this.hdTotal = hdTotal;
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

    public double getCpuUsagePorcentageOshi() {
        cpuUsagePorcentage = hal.getProcessor().getSystemCpuLoadBetweenTicks();
        cpuUsagePorcentage = cpuUsagePorcentage * 100;

        return cpuUsagePorcentage;
    }

    public String getCpuNameOshi() {
        cpuName = hal.getProcessor().getName();
        return cpuName;
    }

    public double getCpuTemperatureOshi() {
        cpuTemperature = si.getHardware().getSensors().getCpuTemperature();
        return cpuTemperature;
    }

    public String getRamTotalOshi() {
        long ramTotal1 = 0;
        ramTotal1 = hal.getMemory().getTotal();
        ramTotal = FormatUtil.formatBytesDecimal(ramTotal1);
        return ramTotal;
    }

    public long getRamUsageOshi() {
        ramUsage = (hal.getMemory().getTotal() - hal.getMemory().getAvailable());
        return ramUsage;
    }

    public long getRamAvailableOshi() {
        ramAvailable = hal.getMemory().getAvailable();
        return ramAvailable;
    }

    public String getHDTotalOshi() {

        List<Long> hds = new ArrayList<>();
        
        long hdTotal1 = 0;

        HWDiskStore[] disks = hal.getDiskStores();
        for (HWDiskStore disk : disks) {
            hds.add(disk.getSize());
        }
        for (int i = 0; i < hds.size(); i++) {
            hdTotal1 = (hdTotal1 + hds.get(i));
        }

        hdTotal = FormatUtil.formatBytesDecimal(hdTotal1); 
        return hdTotal;
    }

    public long getComputerUsageTimeOshi() {
        computerUsageTime = hal.getProcessor().getSystemUptime();
        return computerUsageTime;
    }

    public String getComputerIpAddressOshi() {
        computerIpAddress = si.getOperatingSystem().getNetworkParams().getIpv4DefaultGateway();
        return computerIpAddress;
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
