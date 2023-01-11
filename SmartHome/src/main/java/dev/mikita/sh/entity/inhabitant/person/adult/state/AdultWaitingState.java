package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.core.task.TaskSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import dev.mikita.sh.entity.device.DeviceFactory;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.item.AItem;
import dev.mikita.sh.entity.item.ItemFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing adult waiting state.
 */
public class AdultWaitingState extends AInhabitantState {
    // Constants
    private final double WAITING_TIME_IN_HOUR = 0.4;
    private final int START_WORKING_TIME = 10;
    private final int END_WORKING_TIME = 17;
    private final int START_SLEEPING_TIME = 23;
    private final int END_SLEEPING_TIME = 7;

    public AdultWaitingState(AInhabitant inhabitant) {
        super(inhabitant);
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();
        TaskSystem taskSystem = SHSystem.getInstance().getTaskSystem();

        // Sleeping time
        if (simulationTime.getHour() >= START_SLEEPING_TIME || simulationTime.getHour() < END_SLEEPING_TIME) {
            inhabitant.changeState(new AdultSleepingState(inhabitant));
            return;
        }

        // Work time
        if (simulationTime.getHour() >= START_WORKING_TIME && simulationTime.getHour() < END_WORKING_TIME
                && ((Adult) inhabitant).getGender() == PersonGender.MALE) {
            inhabitant.changeState(new AdultWorkingState(inhabitant));
            return;
        }

        // Check tasks
        if (!taskSystem.isEmpty()) {
            taskSystem.getTask().apply((Adult) inhabitant);
            return;
        }

        // Delay between using items or devices
        if (this.time < WAITING_TIME_IN_HOUR * 3600L * 1000000000L) return;

        if (Math.random() < 0.5) {
            List<ADevice> devices = inhabitant.getRoom().getDevices().stream()
                    .filter(device -> device.getState() instanceof ADeviceIdleState)
                    .collect(Collectors.toList());

            if (devices.isEmpty()) {
                List<ADevice> allDevices = DeviceFactory.getInstance().getDevices().stream()
                        .filter(device -> device.getState() instanceof ADeviceIdleState)
                        .collect(Collectors.toList());

                if (!allDevices.isEmpty()) {
                    ADevice device = allDevices.get((int) (Math.random() * allDevices.size()));
                    inhabitant.moveTo(device.getRoom());
                    device.use(inhabitant);
                }
            } else {
                devices.get((int) (Math.random() * devices.size())).use(inhabitant);
            }
        } else {
            List<AItem> items = inhabitant.getRoom().getItems().stream()
                    .filter(item -> !item.isUsing())
                    .collect(Collectors.toList());

            if (items.isEmpty()) {
                List<AItem> allItems = ItemFactory.getInstance().getItems().stream()
                        .filter(item -> !item.isUsing())
                        .collect(Collectors.toList());

                if (!allItems.isEmpty()) {
                    AItem item = allItems.get((int) (Math.random() * allItems.size()));
                    inhabitant.moveTo(item.getRoom());
                    item.use(inhabitant);
                }
            } else {
                items.get((int) (Math.random() * items.size())).use(inhabitant);
            }
        }
        }
}
