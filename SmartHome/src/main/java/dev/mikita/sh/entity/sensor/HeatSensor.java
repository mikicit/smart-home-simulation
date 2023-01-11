package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.LowTemperatureEvent;
import dev.mikita.sh.event.NormalTemperatureEvent;

/**
 * Class representing a heat sensor.
 */
public class HeatSensor extends AInternalSensor {
    // Constants
    private final double MIN_TEMP = 18;
    private final double MAX_TEMP = 24;

    // State
    private enum HeatSensorState {
        NORMAL,
        COLD
    }

    private HeatSensorState state = HeatSensorState.NORMAL;

    public HeatSensor(Room room) {
        super(room);
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time =+ time;

        double temp = room.getAtmosphere().getTemperature();

        if (temp < MIN_TEMP && state == HeatSensorState.NORMAL) {
            switchState();
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new LowTemperatureEvent(this, room), room.getName());
        } else if (temp > MAX_TEMP && state == HeatSensorState.COLD) {
            switchState();
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new NormalTemperatureEvent(this, room), room.getName());
        }
    }

    /**
     * Switches the state.
     */
    @Override
    protected void switchState() {
        state = state == HeatSensorState.NORMAL ? HeatSensorState.COLD : HeatSensorState.NORMAL;
    }

    /**
     * Resets the state.
     */
    @Override
    public void resetState() {
        state = HeatSensorState.NORMAL;
    }

    /**
     * Returns the state.
     * @return state
     */
    public HeatSensorState getState() {
        return this.state;
    }
}
