package dev.mikita.sh.entity.sensor;

public class SmokeSensor extends ASensor {
    private enum SmokeSensorState {
        BIG_SMOKE,
        NO_SMOKE
    }

    private SmokeSensorState state = SmokeSensorState.NO_SMOKE;

    @Override
    public void update(long time) {

    }

    @Override
    protected void switchState() {
        state = state == SmokeSensorState.NO_SMOKE ? SmokeSensorState.BIG_SMOKE : SmokeSensorState.NO_SMOKE;
    }
}
