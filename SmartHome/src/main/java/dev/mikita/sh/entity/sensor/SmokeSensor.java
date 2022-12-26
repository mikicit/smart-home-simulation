package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.entity.location.Room;

public class SmokeSensor extends AInternalSensor {
    // State
    private enum SmokeSensorState {
        BIG_SMOKE,
        NO_SMOKE
    }

    private SmokeSensorState state = SmokeSensorState.NO_SMOKE;

    public SmokeSensor(Room room) {
        super(room);
    }

    @Override
    public void update(long time) {

    }

    @Override
    protected void switchState() {
        state = state == SmokeSensorState.NO_SMOKE ? SmokeSensorState.BIG_SMOKE : SmokeSensorState.NO_SMOKE;
    }

    @Override
    protected void resetState() {
        state = SmokeSensorState.NO_SMOKE;
    }
}
