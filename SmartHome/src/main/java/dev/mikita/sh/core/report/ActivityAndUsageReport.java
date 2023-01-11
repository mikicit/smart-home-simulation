package dev.mikita.sh.core.report;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.item.AItem;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for generating activity and usage report.
 */
public class ActivityAndUsageReport {
    private final Map<APerson, Map<ADevice, Integer>> devices = new HashMap<>();
    private final Map<APerson, Map<AItem, Integer>> items = new HashMap<>();
    private FileWriter activityAndUsageReport = null;

    /**
     * Instantiates a new Activity and usage report.
     */
    public ActivityAndUsageReport() {
        try {
            this.activityAndUsageReport = new FileWriter("report/ActivityAndUsageReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts used devices.
     *
     * @param person person that uses a device
     * @param device device that is used
     */
    public void deviceCount(APerson person, ADevice device) {
        if (!devices.containsKey(person)) {
            devices.put(person, new HashMap<>());
        }

        if (!devices.get(person).containsKey(device)) {
            devices.get(person).put(device, 0);
        }

        devices.get(person).put(device, devices.get(person).get(device) + 1);
    }

    /**
     * Counts used items.
     *
     * @param person person that uses an item
     * @param item   item that is used
     */
    public void itemCount(APerson person, AItem item) {
        if (!items.containsKey(person)) {
            items.put(person, new HashMap<>());
        }

        if (!items.get(person).containsKey(item)) {
            items.get(person).put(item, 0);
        }

        items.get(person).put(item, items.get(person).get(item) + 1);
    }

    /**
     * Generates report.
     *
     * @throws IOException writing to file is unsuccessful
     */
    public void generateReport() throws IOException {
        int day = SHSystem.getInstance().getSimulation().getDay();

        activityAndUsageReport.write("__________________________________ Report for the " + day + " day __________________________________\n");
        activityAndUsageReport.write("_______________________________________ [Devices] ________________________________________\n");

        for (APerson person : devices.keySet()) {
            for (ADevice device : devices.get(person).keySet()) {
                activityAndUsageReport.write("Person \"" + person.getName() + "\" has used \"" + device.getName() + "\" "
                        + devices.get(person).get(device) + " times this day.\n");
            }
        }

        activityAndUsageReport.write("_______________________________________ [Items] __________________________________________\n");

        for (APerson person : items.keySet()) {
            for (AItem item : items.get(person).keySet()) {
                activityAndUsageReport.write("Person \"" + person.getName() + "\" has used \"" + item.getName() + "\" "
                        + items.get(person).get(item) + " times this day.\n");
            }
        }

        activityAndUsageReport.flush();

        devices.clear();
        items.clear();
    }
}
