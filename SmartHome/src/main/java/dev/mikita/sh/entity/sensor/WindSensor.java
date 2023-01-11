package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.event.NormalWindEvent;
import dev.mikita.sh.event.StrongWindEvent;

/**
 * Class representing a wind sensor.
 */
public class WindSensor extends AExternalSensor {
    // Constants
    private final double MIN_SPEED = 8;
    private final double MAX_SPEED = 12;

    // State
    private enum WindSensorState {
        WIND,
        NO_WIND
    }

    private WindSensorState state = WindSensorState.NO_WIND;

    public WindSensor(House house) {
        super(house);
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time =+ time;

        double windSpeed = house.getAtmosphere().getWindSpeed();

        if (windSpeed < MIN_SPEED && state == WindSensorState.WIND) {
            switchState();
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new NormalWindEvent(this, house), "global");
        } else if (windSpeed > MAX_SPEED && state == WindSensorState.NO_WIND) {
            switchState();
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new StrongWindEvent(this, house), "global");
        }
    }

    /**
     * Switches the state.
     */
    @Override
    protected void switchState() {
        state = state == WindSensorState.WIND ? WindSensorState.NO_WIND : WindSensorState.WIND;
    }

    /**
     * Resets the state.
     */
    @Override
    public void resetState() {
        state = WindSensorState.NO_WIND;
    }

    /**
     * Returns the state.
     * @return state
     */
    public WindSensorState getState() {
        return this.state;
    }
}
