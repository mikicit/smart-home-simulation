package dev.mikita.sh.entity.inhabitant.person.child.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import dev.mikita.sh.entity.device.DeviceFactory;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultDeviceUsingState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultSleepingState;
import dev.mikita.sh.entity.item.AItem;
import dev.mikita.sh.entity.item.ItemFactory;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChildAwakeState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(ChildAwakeState.class.getName());

    public ChildAwakeState(AInhabitant inhabitant) {
        super(inhabitant);
    }

    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();

        // Sleeping time
        if (simulationTime.getHour() >= 21 || simulationTime.getHour() < 8) {
            inhabitant.changeState(new ChildSleepingState(inhabitant));
            return;
        }

        // Indicators
        inhabitant.setHungerIndicator(inhabitant.getHungerIndicator()
                - (inhabitant.getHungerPerHour() / 3600D * 1000000000) * time);
        inhabitant.setLeisureIndicator(inhabitant.getLeisureIndicator()
                - (inhabitant.getLeisurePerHour() / 3600D * 1000000000) * time);

        // Device using
        if (inhabitant.getLeisureIndicator() == 0) {
            List<ADevice> devices = inhabitant.getRoom().getDevices().stream()
                    .filter(device -> device.getState() instanceof ADeviceIdleState)
                    .collect(Collectors.toList());

            if (devices.isEmpty()) {
                log.info(String.format("Child \"%s\" didn't find any available device to play with :( [%s]",
                        inhabitant.getName(),
                        SHSystem.getInstance().getSimulation().getFormattedTime()));
            } else {
                devices.get((int) (Math.random() * devices.size())).use(inhabitant);
            }
        }
    }
}
