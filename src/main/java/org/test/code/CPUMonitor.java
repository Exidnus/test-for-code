package org.test.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

class CPUMonitor implements ICPUMonitor {

    private static final String MPSTAT_COMMAND = "mpstat -P ALL 1 1";
    private static final String WHITE_SPACE_SPLITTER = "\\s+";
    private static final int IDLE_COLUMN_INDEX = 11;

    enum Result {
        OK, FAIL;
    }

    class SomeTest {
        private int number;

        SomeTest() {
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "SomeTest{" +
                    "number=" + number +
                    '}';
        }
    }

    @Override
    public double getCpuUsage() {
        return getAllCpuUsage().get("All");
    }

    @Override
    public int getCountOfCores() {
        return getAllCpuUsage().size() - 1;
    }

    @Override
    public String toString() {
        return getAllCpuUsage().toString();
    }

    @Override
    public Map<String, Double> getAllCpuUsage() {
        final Process process = executeMpstatCommand();
        try (InputStream is = process.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            skipThreeLines(reader);
            final Map<String, Double> cpuUsage = new HashMap<>();
            putAllCpuUsage(cpuUsage, reader);
            putEveryCoreUsage(cpuUsage, reader);

            return cpuUsage;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Process executeMpstatCommand() {
        try {
            return Runtime.getRuntime().exec(MPSTAT_COMMAND);
        } catch (IOException e) {
            throw new RuntimeException("Can't execute mpstat command.\nMake sure " +
                    "that you use Linux and have mpstat", e);
        }
    }

    private void skipThreeLines(final BufferedReader reader) throws IOException {
        for (int i = 0; i < 3; i++) {
            reader.readLine();
        }
    }

    private void putAllCpuUsage(final Map<String, Double> cpuUsage, final BufferedReader reader) throws IOException {
        final String averageStatistics = reader.readLine();
        Objects.requireNonNull(averageStatistics);
        final String[] splittedStrings = averageStatistics.replaceAll(",", ".").split(WHITE_SPACE_SPLITTER);
        final Double idleValue = Double.parseDouble(splittedStrings[IDLE_COLUMN_INDEX]);
        cpuUsage.put("All", 100 - idleValue);
    }

    private void putEveryCoreUsage(final Map<String, Double> cpuUsage, final BufferedReader reader) throws IOException {
        int i = 0;
        String statisticsAboutCurrentCore;
        while ((statisticsAboutCurrentCore = reader.readLine()) != null && statisticsAboutCurrentCore.length() > 1) {
            final String[] splittedStrings = statisticsAboutCurrentCore.replaceAll(",", ".").split(WHITE_SPACE_SPLITTER);
            final Double idleValue = Double.parseDouble(splittedStrings[IDLE_COLUMN_INDEX]);
            cpuUsage.put(String.valueOf(i), 100 - idleValue);
            ++i;
        }
    }

    @Deprecated
    public double GET_cpuUsage() {
        return getCpuUsage();
    }

    @Deprecated
    @Override
    public Map<String, Double> gEtaLL_CPU_Usage() {
        return getAllCpuUsage();
    }

    @Deprecated
    @Override
    public int get_Count_Of_CoreМетод() {
        return getCountOfCores();
    }
}
