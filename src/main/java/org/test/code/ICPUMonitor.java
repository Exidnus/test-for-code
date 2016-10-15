package org.test.code;

import java.util.Map;

public interface ICPUMonitor {

    Map<String, Double> getAllCpuUsage();

    int getCountOfCores();

    double getCpuUsage();

    static ICPUMonitor getLinuxMpstatImpl() {
        return new CPUMonitor();
    }
}
