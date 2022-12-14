package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.entity.location.House;

public class WindSensor extends AExternalSensor {
    private enum WindSensorState {
        WIND,
        NO_WIND
    }

    private WindSensorState state = WindSensorState.NO_WIND;

    public WindSensor(House house) {
        super(house);
    }

    @Override
    public void update(long time) {

    }

    @Override
    protected void switchState() {
        state = state == WindSensorState.WIND ? WindSensorState.NO_WIND : WindSensorState.WIND;
    }
}
