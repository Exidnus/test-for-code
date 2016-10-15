package org.test.code;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by dmitriy_varygin on 15.10.16.
 */
public class CPUMonitorTest {

    private final ICPUMonitor cpuMonitor = new CPUMonitor();

    @Test
    public void shouldGetAllCpuUsage() {
        final Map<String, Double> cpuUsage = cpuMonitor.gEtaLL_CPU_Usage();
        assertNotNull(cpuUsage);
        assertTrue(cpuUsage.size() > 0);

        assertTrue(cpuUsage.containsKey("All"));
        assertNotNull(cpuUsage.get("All"));
        assertNotNull(cpuUsage.get("0"));

        System.out.println(cpuUsage);
    }

    @Test
    public void shouldGetCountOfCore() {
        final int count = cpuMonitor.get_Count_Of_CoreМетод();
        assertTrue(count > 0);
        System.out.println(count);
    }
}