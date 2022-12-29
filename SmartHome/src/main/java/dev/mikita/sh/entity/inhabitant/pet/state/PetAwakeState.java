package dev.mikita.sh.entity.inhabitant.pet.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import dev.mikita.sh.entity.device.DeviceFactory;
import dev.mikita.sh.entity.device.fridge.state.FridgeIdleState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.pet.APet;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PetAwakeState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeIdleState.class.getName());

    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 6;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 24;

    // State
    private long timeFromLastDispatchedDeviceUsingEvent = 0;
    private double triggeredTimeInHours;

    public PetAwakeState(AInhabitant inhabitant) {
        super(inhabitant);
        this.triggeredTimeInHours = calculateTriggeredTime();
    }

    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    private void resetDeviceUsingEvent() {
        this.triggeredTimeInHours = calculateTriggeredTime();
        this.timeFromLastDispatchedDeviceUsingEvent = 0;

        log.info(String.format("%s \"%s\" played with object [%s]",
                inhabitant.getClass().getSimpleName(),
                inhabitant.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedDeviceUsingEvent += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();

        // Sleeping time
        if (!((APet) inhabitant).getDispatchedHungerEvent() && !((APet) inhabitant).getDispatchedPlayedEvent()) {
            if (simulationTime.getHour() >= 23 || simulationTime.getHour() < 7) {
                inhabitant.changeState(new PetSleepingState(inhabitant));
                return;
            }
        }

        // Device using
        if (this.timeFromLastDispatchedDeviceUsingEvent >= triggeredTimeInHours * 3600L * 1000000000L) {
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
                    resetDeviceUsingEvent();
                }
            } else {
                devices.get((int) (Math.random() * devices.size())).use(inhabitant);
                resetDeviceUsingEvent();
            }
        }
    }
}
