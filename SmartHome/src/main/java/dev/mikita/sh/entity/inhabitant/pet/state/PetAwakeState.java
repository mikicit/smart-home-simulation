package dev.mikita.sh.entity.inhabitant.pet.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import dev.mikita.sh.entity.device.DeviceFactory;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildAwakeState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildSleepingState;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PetAwakeState extends AInhabitantState {
    public PetAwakeState(AInhabitant inhabitant) {
        super(inhabitant);
    }

    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();

        // Sleeping time
        if (simulationTime.getHour() >= 23 || simulationTime.getHour() < 7) {
            inhabitant.changeState(new PetSleepingState(inhabitant));
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
        }

    }
}
