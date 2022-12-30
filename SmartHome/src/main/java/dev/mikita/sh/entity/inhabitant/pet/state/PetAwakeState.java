package dev.mikita.sh.entity.inhabitant.pet.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceBrokenState;
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
                    useDeviceAndResetDeviceUsingEvent(device);
                }
            } else {
                ADevice device = devices.get((int) (Math.random() * devices.size()));
                useDeviceAndResetDeviceUsingEvent(device);
            }
        }
    }
}
