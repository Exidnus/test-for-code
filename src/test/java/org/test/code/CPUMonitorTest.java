package org.test.code;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by dmitriy_varygin on 15.10.16.
 */
public class CPUMonitorTest {

    private final ICPUMonitor cpuMonitor = ICPUMonitor.getLinuxMpstatImpl();

    @Test
    public void shouldGetAllCpuUsage() {
        final Map<String, Double> cpuUsage = cpuMonitor.getAllCpuUsage();
        assertNotNull(cpuUsage);
        assertTrue(cpuUsage.size() > 0);

        assertTrue(cpuUsage.containsKey("All"));
        assertNotNull(cpuUsage.get("All"));
        cpuUsage.values().forEach(Assert::assertNotNull);

        System.out.println(cpuUsage);
    }

    @Test
    public void shouldGetCountOfCore() {
        final int count = cpuMonitor.getCountOfCores();
        assertTrue(count > 0);
        System.out.println("Count of cores: " + count);
    }

    @Test
    public void shouldGetCpuUsage() {
        final double cpuUsage = cpuMonitor.getCpuUsage();
        assertTrue(cpuUsage > 0.0);
        System.out.println("CPU Usage: " + cpuUsage);
    }

    @Test
    public void shouldGetStringWorkingFine() {
        final String str = cpuMonitor.toString();
        assertNotNull(str);
        assertTrue(str.length() > 0);
        System.out.println(str);
    }
}