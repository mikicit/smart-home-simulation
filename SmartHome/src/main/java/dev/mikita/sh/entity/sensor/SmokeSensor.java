package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.SmokeInRoomEvent;

public class SmokeSensor extends AInternalSensor {
    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 5;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 50;

    // State
    private enum SmokeSensorState {
        BIG_SMOKE,
        NO_SMOKE
    }

    private SmokeSensorState state = SmokeSensorState.NO_SMOKE;
    private double triggeredTimeInHours;

    public SmokeSensor(Room room) {
        super(room);
        this.triggeredTimeInHours = calculateTriggeredTime();
    }

    private double calculateTriggeredTime() {
       return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    @Override
    public void update(long time) {
        this.time += time;

        if (state == SmokeSensorState.NO_SMOKE && this.time >= triggeredTimeInHours * 3600L * 1000000000L) {
            switchState();
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new SmokeInRoomEvent(this, room), room.getName());
        }
    }

    @Override
    protected void switchState() {
        state = state == SmokeSensorState.NO_SMOKE ? SmokeSensorState.BIG_SMOKE : SmokeSensorState.NO_SMOKE;
    }

    @Override
    protected void resetState() {
        this.time = 0;
        state = SmokeSensorState.NO_SMOKE;
        triggeredTimeInHours = calculateTriggeredTime();
    }
}
