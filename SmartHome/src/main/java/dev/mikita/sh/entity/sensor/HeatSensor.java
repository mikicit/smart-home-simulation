package dev.mikita.sh.entity.sensor;

public class HeatSensor extends ASensor {
    private enum HeatSensorState {
        HELL,
        COLD
    }

    private HeatSensorState state = HeatSensorState.COLD;
    private double minTemp;
    private double maxTemp;

    public HeatSensor(double minTemp, double maxTemp) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    @Override
    public void update(long time) {

    }

    @Override
    protected void switchState() {
        state = state == HeatSensorState.HELL ? HeatSensorState.COLD : HeatSensorState.HELL;
    }
}
