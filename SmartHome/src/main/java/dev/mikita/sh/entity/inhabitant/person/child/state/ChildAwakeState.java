package dev.mikita.sh.entity.inhabitant.person.child.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.child.Child;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Class representing child awake state.
 */
public class ChildAwakeState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(ChildAwakeState.class.getName());

    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 2;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 24;
    private final int START_SLEEPING_TIME = 21;
    private final int END_SLEEPING_TIME =  8;

    // State
    private long timeFromLastDispatchedDeviceUsingEvent = 0;
    private double triggeredTimeInHours;

    public ChildAwakeState(AInhabitant inhabitant) {
        super(inhabitant);
        this.triggeredTimeInHours = calculateTriggeredTime();
    }

    /**
     * Calculates device using triggered time.
     * @return time
     */
    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    /**
     * Uses the device and resets the time.
     * @param device the device
     */
    private void useDeviceAndResetDeviceUsingEvent(ADevice device) {
        this.timeFromLastDispatchedDeviceUsingEvent = 0;
        this.triggeredTimeInHours = calculateTriggeredTime();

        if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
            log.info(String.format("%s \"%s\" broke the object \"%s\" while playing with it :( [%s]",
                    inhabitant.getClass().getSimpleName(),
                    inhabitant.getName(),
                    device.getName(),
                    SHSystem.getInstance().getSimulation().getFormattedTime()));

            device.toBreak();
        } else {
            log.info(String.format("%s \"%s\" didn't break the object \"%s\" while playing with it, HOORAY :D [%s]",
                    inhabitant.getClass().getSimpleName(),
                    inhabitant.getName(),
                    device.getName(),
                    SHSystem.getInstance().getSimulation().getFormattedTime()));
        }
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedDeviceUsingEvent += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();

        // Sleeping time
        if (!((Child) inhabitant).getDispatchedHungerEvent() && !((Child) inhabitant).getDispatchedPoopedEvent()) {
            if (simulationTime.getHour() >= START_SLEEPING_TIME || simulationTime.getHour() < END_SLEEPING_TIME) {
                inhabitant.changeState(new ChildSleepingState(inhabitant));
                return;
            }
        }

        // Device using
        if (this.timeFromLastDispatchedDeviceUsingEvent >= triggeredTimeInHours * 3600L * 1000000000L) {
            List<ADevice> devices = inhabitant.getRoom().getDevices().stream()
                    .filter(device -> device.getState() instanceof ADeviceIdleState)
                    .collect(Collectors.toList());

            if (devices.isEmpty()) {
                log.info(String.format("Child \"%s\" didn't find any available device in room \"%s\" :( [%s]",
                        inhabitant.getName(),
                        inhabitant.getRoom().getName(),
                        SHSystem.getInstance().getSimulation().getFormattedTime()));
            } else {
                ADevice device = devices.get((int) (Math.random() * devices.size()));
                useDeviceAndResetDeviceUsingEvent(device);
            }
        }
    }
}
