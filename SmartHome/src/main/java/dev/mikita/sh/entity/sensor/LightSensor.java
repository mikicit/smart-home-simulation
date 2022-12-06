package dev.mikita.sh.entity.sensor;

public class LightSensor extends ASensor {
    private enum LightSensorState {
        ENOUGH_LIGHT,
        NOT_ENOUGH_LIGHT
    }

    private LightSensorState state = LightSensorState.ENOUGH_LIGHT;

    @Override
    public void update(long time) {

    }

    @Override
    protected void switchState() {
        state = state == LightSensorState.ENOUGH_LIGHT ? LightSensorState.NOT_ENOUGH_LIGHT : LightSensorState.ENOUGH_LIGHT;
    }
}
