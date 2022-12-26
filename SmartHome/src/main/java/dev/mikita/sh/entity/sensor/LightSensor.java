package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.entity.location.Room;

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

    @Override
    public void update(long time) {

    }

    @Override
    protected void switchState() {
        state = state == LightSensorState.ENOUGH_LIGHT ? LightSensorState.NOT_ENOUGH_LIGHT : LightSensorState.ENOUGH_LIGHT;
    }

    @Override
    protected void resetState() {
        state = LightSensorState.ENOUGH_LIGHT;
    }
}
