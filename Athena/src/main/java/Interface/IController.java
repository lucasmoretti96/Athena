package Interface;

import Statements.MachinesRepository;
import com.sun.corba.se.spi.activation.Repository;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public interface IController {
    SystemInfo si = new SystemInfo();
    OperatingSystem os = si.getOperatingSystem();
    HardwareAbstractionLayer hal = si.getHardware();
    MachinesRepository repository = new MachinesRepository();
}
