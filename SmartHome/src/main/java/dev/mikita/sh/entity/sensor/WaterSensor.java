package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.entity.location.Room;
import java.io.IOException;

public class WaterSensor extends AInternalSensor {
    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 5;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 50;

    // State
    private enum WaterSensorState {
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

    }

    @Override
    protected void switchState() {
        state = state == WaterSensorState.NO_WATER ? WaterSensorState.WATER : WaterSensorState.NO_WATER;
    }

    @Override
    protected void resetState() {

    }
}
