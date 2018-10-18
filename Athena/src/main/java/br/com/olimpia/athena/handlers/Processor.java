package br.com.olimpia.athena.handlers;

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

public class Processor {
    private double cpuUsage;
    
    SystemInfo si = new SystemInfo();
    OperatingSystem os = si.getOperatingSystem();
    HardwareAbstractionLayer hal = si.getHardware();
    
    public String getName()
    {
        return hal.getProcessor().getName();
    }   
}