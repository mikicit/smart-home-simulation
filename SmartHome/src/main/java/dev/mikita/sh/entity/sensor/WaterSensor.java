package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.WaterInRoomEvent;

import java.io.IOException;

public class WaterSensor extends AInternalSensor {
    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 48;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 192;

    // State
    enum WaterSensorState {
        WATER,
        NO_WATER
    }

    private WaterSensorState state = WaterSensorState.NO_WATER;
    private double triggeredTimeInHours;

    public WaterSensor(Room room) {
        super(room);
        this.triggeredTimeInHours = calculateTriggeredTime();
    }

    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    @Override
    public void update(long time) throws IOException {
        this.time += time;

        if (state == WaterSensorState.NO_WATER && this.time >= triggeredTimeInHours * 3600L * 1000000000L) {
            switchState();
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new WaterInRoomEvent(this, room), "global");
        }
    }

    @Override
    protected void switchState() {
        state = state == WaterSensorState.NO_WATER ? WaterSensorState.WATER : WaterSensorState.NO_WATER;
    }

    @Override
    public void resetState() {
        this.time = 0;
        state = WaterSensorState.NO_WATER;
        triggeredTimeInHours = calculateTriggeredTime();
    }

    public WaterSensorState getState() {
        return this.state;
    }
}
