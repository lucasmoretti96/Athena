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

    private String cpuName;
    private String computerIpAddress;
    private String ramTotal;
    private String hdTotal;
    

    public Computer() {
    }

    public Computer(String cpuName,
            String computerIpAddress, 
            String ramTotal, 
            String hdTotal) {
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

    public String getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(String ramTotal) {
        this.ramTotal = ramTotal;
    }

    public String getHdTotal() {
        return hdTotal;
    }

    public void setHdTotal(String hdTotal) {
        this.hdTotal = hdTotal;
    }

    public String getCpuNameOshi() {
        cpuName = hal.getProcessor().getName();
        return cpuName;
    }

    public String getRamTotalOshi() {
        long ramTotal1 = 0;
        ramTotal1 = hal.getMemory().getTotal();
        ramTotal = FormatUtil.formatBytesDecimal(ramTotal1);
        return ramTotal;
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

    public String getComputerIpAddressOshi() {
        computerIpAddress = si.getOperatingSystem().getNetworkParams().getIpv4DefaultGateway();
        return computerIpAddress;
    }
}
