package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.time.SimulationTime;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import dev.mikita.sh.entity.device.DeviceFactory;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.item.AItem;
import dev.mikita.sh.entity.item.ItemFactory;

import java.util.List;
import java.util.stream.Collectors;

public class AdultWaitingState extends AInhabitantState {
    public AdultWaitingState(AInhabitant inhabitant) {
        super(inhabitant);
    }

    @Override
    public void update(long time) {
//        this.time += time;
//
//        SimulationTime simulationTime = SHSystem.getInstance().getTimer();
//
//        // Sleeping time
//        if (simulationTime.getHour() >= 23 || simulationTime.getHour() < 7) {
//            inhabitant.changeState(new AdultSleepingState(inhabitant));
//            return;
//        }
//
//        // Indicators
//        inhabitant.setHungerIndicator(inhabitant.getHungerIndicator() - (inhabitant.getHungerPerHour() / 3600F * 1000000000) * time);
//        inhabitant.setLeisureIndicator(inhabitant.getLeisureIndicator() - (inhabitant.getLeisurePerHour() / 3600F * 1000000000) * time);
//
//        if (inhabitant.getLeisureIndicator() == 0) {
//            if (Math.random() < 0.5) {
//                List<ADevice> devices = inhabitant.getRoom().getDevices().stream()
//                        .filter(device -> device.getState() instanceof ADeviceIdleState)
//                        .collect(Collectors.toList());
//
//                if (devices.isEmpty()) {
//                    List<ADevice> allDevices = DeviceFactory.getInstance().getDevices().stream()
//                            .filter(device -> device.getState() instanceof ADeviceIdleState)
//                            .collect(Collectors.toList());
//
//                    if (!allDevices.isEmpty()) {
//                        ADevice device = allDevices.get((int) (Math.random() * allDevices.size()));
//                        inhabitant.moveTo(device.getRoom());
//                        device.use(inhabitant);
//                    }
//                } else {
//                    devices.get((int) (Math.random() * devices.size())).use(inhabitant);
//                }
//            } else {
//                List<AItem> items = inhabitant.getRoom().getItems().stream()
//                        .filter(item -> !item.isUsing())
//                        .collect(Collectors.toList());
//
//                if (items.isEmpty()) {
//                    List<AItem> allItems = ItemFactory.getInstance().getItems().stream()
//                            .filter(item -> !item.isUsing())
//                            .collect(Collectors.toList());
//
//                    if (!allItems.isEmpty()) {
//                        AItem item = allItems.get((int) (Math.random() * allItems.size()));
//                        inhabitant.moveTo(item.getRoom());
//                        item.use(inhabitant);
//                    }
//                } else {
//                    items.get((int) (Math.random() * items.size())).use(inhabitant);
//                }
//            }
//        }
    }
}
