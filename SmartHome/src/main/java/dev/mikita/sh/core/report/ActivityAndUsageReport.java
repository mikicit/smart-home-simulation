package dev.mikita.sh.core.report;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.item.AItem;

import java.util.HashMap;
import java.util.Map;

public class ActivityAndUsageReport {
    private final Map<APerson, Map<ADevice, Integer>> devices = new HashMap<>();
    private final Map<APerson, Map<AItem, Integer>> items = new HashMap<>();

    public void deviceCount(APerson person, ADevice device) {
        if (!devices.containsKey(person)) {
            devices.put(person, new HashMap<>());
        }

        if (!devices.get(person).containsKey(device)) {
            devices.get(person).put(device, 0);
        }

        devices.get(person).put(device, devices.get(person).get(device) + 1);
    }

    public void itemCount(APerson person, AItem item) {
        if (!items.containsKey(person)) {
            items.put(person, new HashMap<>());
        }

        if (!items.get(person).containsKey(item)) {
            items.get(person).put(item, 0);
        }

        items.get(person).put(item, items.get(person).get(item) + 1);
    }

    public void generate() {

    }
}
