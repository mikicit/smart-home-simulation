package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.WaterInRoomEvent;

import java.io.IOException;

/**
 * Class representing a water sensor.
 */
public class WaterSensor extends AInternalSensor {
    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 48;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 192;

    // State
    public enum WaterSensorState {
        WATER,
        NO_WATER
    }

    private WaterSensorState state = WaterSensorState.NO_WATER;
    private double triggeredTimeInHours;

    public WaterSensor(Room room) {
        super(room);
        this.triggeredTimeInHours = calculateTriggeredTime();
    }

    /**
     * Calculates an event triggered time.
     * @return time
     */
    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    /**
     * Update.
     * @param time the time
     * @throws IOException exception
     */
    @Override
    public void update(long time) throws IOException {
        this.time += time;

        if (state == WaterSensorState.NO_WATER && this.time >= triggeredTimeInHours * 3600L * 1000000000L) {
            switchState();
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new WaterInRoomEvent(this, room), "global");
        }
    }

    /**
     * Switches the state.
     */
    @Override
    protected void switchState() {
        state = state == WaterSensorState.NO_WATER ? WaterSensorState.WATER : WaterSensorState.NO_WATER;
    }

    /**
     * Resets the state.
     */
    @Override
    public void resetState() {
        this.time = 0;
        state = WaterSensorState.NO_WATER;
        triggeredTimeInHours = calculateTriggeredTime();
    }

    /**
     * Returns the state.
     * @return state
     */
    public WaterSensorState getState() {
        return this.state;
    }
}
