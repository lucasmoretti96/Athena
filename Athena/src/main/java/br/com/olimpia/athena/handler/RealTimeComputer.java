package br.com.olimpia.athena.handler;

import oshi.software.os.OSFileStore;

public class RealTimeComputer implements Interface.IController{
    private long ramUsage;
    private long ramAvailable;
    private long computerUsageTime;
    private OSFileStore[] hdsDetails;
    private double cpuTemperature;
    private double cpuUsagePorcentage;
    private long hdTotal;
    private long hdUsage;
    
    public RealTimeComputer(){
    }
    
    public RealTimeComputer(
            long computerUsageTime, 
            long ramUsage, 
            long ramAvailable, 
            double cpuUsagePorcentage, 
            double cpuTemperature,
            long hdTotal,
            long hdUsage){
        this.computerUsageTime = computerUsageTime;
        this.ramUsage = ramUsage;
        this.ramAvailable = ramAvailable;
        this.cpuUsagePorcentage = cpuUsagePorcentage;
        this.cpuTemperature = cpuTemperature;
        this.hdTotal = hdTotal;
        this.hdUsage = hdUsage;
    }
    public RealTimeComputer getRealTimeComputer(){
        return new RealTimeComputer(
                getComputerUsageTimeOshi(),
                getRamUsageOshi(),
                getRamAvailableOshi(),
                getCpuUsagePorcentageOshi(),
                getCpuTemperatureOshi(),
                getHdTotalOshi(),
                getHdUsageOshi());
    }
        public long getHdTotal(){
        return hdTotal;
    }
        public long getHdUsage(){
        return hdUsage;
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
        Math.round(cpuUsagePorcentage);

        return cpuUsagePorcentage;
    }
    public double getCpuTemperatureOshi() {
        cpuTemperature = si.getHardware().getSensors().getCpuTemperature();
        return cpuTemperature;
    }
       public long getHdTotalOshi(){
           OSFileStore[] fileStores = si.getOperatingSystem().getFileSystem().getFileStores();
           return hdTotal = fileStores[0].getTotalSpace() + fileStores[1].getTotalSpace();
       }
       public long getHdUsageOshi(){
           OSFileStore[] fileStores = si.getOperatingSystem().getFileSystem().getFileStores();
           long total = fileStores[0].getTotalSpace() + fileStores[0].getTotalSpace();
           long usage = fileStores[0].getUsableSpace() + fileStores[0].getUsableSpace();
           hdUsage = (total-usage);
           return hdUsage;
       }
}
