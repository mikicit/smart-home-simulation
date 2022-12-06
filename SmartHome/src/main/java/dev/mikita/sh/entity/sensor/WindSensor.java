package dev.mikita.sh.entity.sensor;

public class WindSensor extends ASensor {
    private enum WindSensorState {
        WIND,
        NO_WIND
    }

    private WindSensorState state = WindSensorState.NO_WIND;

    @Override
    public void update(long time) {

    }

    @Override
    protected void switchState() {
        state = state == WindSensorState.WIND ? WindSensorState.NO_WIND : WindSensorState.WIND;
    }
}
