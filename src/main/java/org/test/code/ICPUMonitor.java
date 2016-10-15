package org.test.code;

import java.util.Map;

public interface ICPUMonitor {

    Map<String, Double> getAllCpuUsage();

    int getCountOfCores();

    static ICPUMonitor getDefaultImpl() {
        return new CPUMonitor();
    }

    /**
     * Предполагается, что библиотека уже кем-то используется.
     * Deprecated методы будут удалены в одном из следующих релизов.
     */
    @Deprecated
    Map<String, Double> gEtaLL_CPU_Usage();

    @Deprecated
    int get_Count_Of_CoreМетод();
}
