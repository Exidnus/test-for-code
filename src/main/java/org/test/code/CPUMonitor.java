package org.test.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class CPUMonitor implements ICPUMonitor {

    private static final String MPSTAT_COMMAND = "mpstat -P ALL 1 1";

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
        final Process process = executeMpstatCommand();
        try (InputStream is = process.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            reader.readLine();
            reader.readLine();
            reader.readLine();

            String currentLine = reader.readLine();
            Objects.requireNonNull(currentLine);

            final int idleColumnIndex = 11;
            String[] s = currentLine.replaceAll(",", ".").split("\\s+");
            Double idleValue = Double.parseDouble(s[idleColumnIndex]);

            final Map<String, Double> cpuUsageList = new HashMap<>();
            cpuUsageList.put("All", 100 - idleValue);

            int i = 0;

            while ((currentLine = reader.readLine()) != null && currentLine.length() > 1) {
                s = currentLine.replaceAll(",", ".").split("\\s+");
                idleValue = Double.parseDouble(s[idleColumnIndex]);

                cpuUsageList.put(String.valueOf(i), 100 - idleValue);
                ++i;
            }

            return cpuUsageList.get("All");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Map<String, Double> getAllCpuUsage() {
        final Process process = executeMpstatCommand();
        try (InputStream is = process.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            reader.readLine();
            reader.readLine();
            reader.readLine();

            String currentLine = reader.readLine();
            Objects.requireNonNull(currentLine);

            final int idleColumnIndex = 11;
            String[] splittedString = currentLine.replaceAll(",", ".").split("\\s+");
            Double idleValue = Double.parseDouble(splittedString[idleColumnIndex]);

            final Map<String, Double> cpuUsageList = new HashMap<>();
            cpuUsageList.put("All", 100 - idleValue);

            int i = 0;

            while ((currentLine = reader.readLine()) != null && currentLine.length() > 1) {
                splittedString = currentLine.replaceAll(",", ".").split("\\s+");
                idleValue = Double.parseDouble(splittedString[idleColumnIndex]);

                cpuUsageList.put(String.valueOf(i), 100 - idleValue);
                ++i;
            }

            return cpuUsageList;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public int getCountOfCores() {
        final Process process = executeMpstatCommand();
        try (InputStream is = process.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {

            reader.readLine();
            reader.readLine();
            reader.readLine();

            String currentLine = reader.readLine();
            Objects.requireNonNull(currentLine);

            final int idleColumnIndex = 11;
            String[] splittedString = currentLine.replaceAll(",", ".").split("\\s+");
            Double idleValue = Double.parseDouble(splittedString[idleColumnIndex]);

            Map<String, Double> cpuUsageList = new HashMap<String, Double>();
            cpuUsageList.put("All", 100 - idleValue);

            int i = 0;

            while ((currentLine = reader.readLine()) != null && currentLine.length() > 1) {
                splittedString = currentLine.replaceAll(",", ".").split("\\s+");
                idleValue = Double.parseDouble(splittedString[idleColumnIndex]);

                cpuUsageList.put(String.valueOf(i), 100 - idleValue);
                ++i;
            }

            return cpuUsageList.size() - 1;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    // Преобразовать в строчку
    public String toString() {
        final Process process = executeMpstatCommand();
        try (InputStream is = process.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {

            reader.readLine();
            reader.readLine();
            reader.readLine();

            String currentLine = reader.readLine();
            Objects.requireNonNull(currentLine);

            final int idleColumnIndex = 11;
            String[] splittedString = currentLine.replaceAll(",", ".").split("\\s+");
            Double idleValue = Double.parseDouble(splittedString[idleColumnIndex]);

            final Map<String, Double> cpuUsageList = new HashMap<>();
            cpuUsageList.put("All", 100 - idleValue);

            int i = 0;

            while ((currentLine = reader.readLine()) != null && currentLine.length() > 1) {
                splittedString = currentLine.replaceAll(",", ".").split("\\s+");
                idleValue = Double.parseDouble(splittedString[idleColumnIndex]);

                cpuUsageList.put(String.valueOf(i), 100 - idleValue);
                ++i;
            }

            return cpuUsageList.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return "";
    }

    private Process executeMpstatCommand() {
        try {
            return Runtime.getRuntime().exec(MPSTAT_COMMAND);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
