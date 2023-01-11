package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.entity.location.Room;

/**
 * Class representing a light sensor.
 */
public class LightSensor extends AInternalSensor {
    // State
    private enum LightSensorState {
        ENOUGH_LIGHT,
        NOT_ENOUGH_LIGHT
    }

    private LightSensorState state = LightSensorState.ENOUGH_LIGHT;

    public LightSensor(Room room) {
        super(room);
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time =+ time;
    }

    /**
     * Switches the state.
     */
    @Override
    protected void switchState() {
        state = state == LightSensorState.ENOUGH_LIGHT ? LightSensorState.NOT_ENOUGH_LIGHT : LightSensorState.ENOUGH_LIGHT;
    }

    /**
     * Resets the state.
     */
    @Override
    public void resetState() {
        state = LightSensorState.ENOUGH_LIGHT;
    }

    /**
     * Returns the state.
     * @return state
     */
    public LightSensorState getState() {
        return this.state;
    }
}
