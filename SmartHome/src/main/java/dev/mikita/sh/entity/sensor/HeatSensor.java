package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.LowTemperatureEvent;
import dev.mikita.sh.event.NormalTemperatureEvent;

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

    @Override
    protected void switchState() {
        state = state == HeatSensorState.NORMAL ? HeatSensorState.COLD : HeatSensorState.NORMAL;
    }

    @Override
    public void resetState() {
        state = HeatSensorState.NORMAL;
    }
}
