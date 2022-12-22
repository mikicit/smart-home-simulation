package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.LowTemperature;
import dev.mikita.sh.event.NormalTemperature;

//TODO при мануальном вкл/откл обогревателя имплементировать сброс сенсора

public class HeatSensor extends AInternalSensor {
    private enum HeatSensorState {
        NORMAL,
        COLD
    }

    private final double MIN_TEMP = 18;
    private final double MAX_TEMP = 24;

    private HeatSensorState state = HeatSensorState.NORMAL;

    public HeatSensor(Room room) {
        super(room);
    }

    @Override
    public void update(long time) {
        double temp = room.getAtmosphere().getTemperature();

        if (temp < MIN_TEMP && state == HeatSensorState.NORMAL) {
            switchState();
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new LowTemperature(this, room), room.toString());
        } else if (temp > MAX_TEMP && state == HeatSensorState.COLD) {
            switchState();
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new NormalTemperature(this, room), room.toString());
        }
    }

    @Override
    protected void switchState() {
        state = state == HeatSensorState.NORMAL ? HeatSensorState.COLD : HeatSensorState.NORMAL;
    }
}
