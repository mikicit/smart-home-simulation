package dev.mikita.sh.entity.inhabitant.person.child.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.child.Child;

import java.util.logging.Logger;

public class ChildAwakeState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(ChildAwakeState.class.getName());

    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 2;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 24;

    public ChildAwakeState(AInhabitant inhabitant) {
        super(inhabitant);
    }

    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();

        // Sleeping time
        if (!((Child) inhabitant).getDispatchedHungerEvent() && !((Child) inhabitant).getDispatchedPoopedEvent()) {
            if (simulationTime.getHour() >= 21 || simulationTime.getHour() < 8) {
                inhabitant.changeState(new ChildSleepingState(inhabitant));
                return;
            }
        }

//        // Device using
//        if (inhabitant.getLeisureIndicator() == 0) {
//            List<ADevice> devices = inhabitant.getRoom().getDevices().stream()
//                    .filter(device -> device.getState() instanceof ADeviceIdleState)
//                    .collect(Collectors.toList());
//
//            if (devices.isEmpty()) {
//                log.info(String.format("Child \"%s\" didn't find any available device to play with :( [%s]",
//                        inhabitant.getName(),
//                        SHSystem.getInstance().getSimulation().getFormattedTime()));
//            } else {
//                devices.get((int) (Math.random() * devices.size())).use(inhabitant);
//            }
//        }
    }
}
