package dev.mikita.sh.core.report;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.person.PersonFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityAndUsageReport {
//    private final Map<APerson, Map<ADevice, Integer>> devices = new HashMap<>();
//    private final Map<APerson, Map<AItem, Integer>> items = new HashMap<>();
    private FileWriter activityAndUsageReport = null;

    public ActivityAndUsageReport() {
        try {
            this.activityAndUsageReport = new FileWriter("report/ActivityAndUsageReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void deviceCount(APerson person, ADevice device) {
//        if (!devices.containsKey(person)) {
//            devices.put(person, new HashMap<>());
//        }
//
//        if (!devices.get(person).containsKey(device)) {
//            devices.get(person).put(device, 0);
//        }
//
//        devices.get(person).put(device, devices.get(person).get(device) + 1);
//    }
//
//    public void itemCount(APerson person, AItem item) {
//        if (!items.containsKey(person)) {
//            items.put(person, new HashMap<>());
//        }
//
//        if (!items.get(person).containsKey(item)) {
//            items.get(person).put(item, 0);
//        }
//
//        items.get(person).put(item, items.get(person).get(item) + 1);
//    }

    public void generateReport() throws IOException {
        List<APerson> allPersons = new ArrayList<>(PersonFactory.getInstance().getPersons());
        int day = SHSystem.getInstance().getSimulation().getDay();

        activityAndUsageReport.write("__________________________________ Report for the " + day + " day __________________________________\n");

        for (APerson p : allPersons) {
            Map<AInhabitant, Map<IUsableObject, Integer>> allUsedObjects = p.getUsedObjects();
            for (AInhabitant person : allUsedObjects.keySet()) {
                for (IUsableObject obj : allUsedObjects.get(person).keySet()) {
                    activityAndUsageReport.write(person.getClass().getSimpleName() + " " + person.getName()
                            + " has used " + obj.getName() + " " + allUsedObjects.get(person).get(obj)
                            + " times this day.\n");
                }
            }
            allUsedObjects.clear();
        }

        activityAndUsageReport.flush();
    }
}
