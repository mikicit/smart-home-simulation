package dev.mikita.sh.entity.device.heater;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.heater.state.HeaterIdleState;
import dev.mikita.sh.entity.device.heater.state.HeaterOffState;
import dev.mikita.sh.entity.device.heater.state.HeaterUsingState;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.LowTemperature;
import dev.mikita.sh.event.NormalTemperature;

public class Heater extends ADevice {
    private final double HEATING_PER_HOUR = 5;

    public Heater(Room room, String name) {
        super(room, name);
        this.state = new HeaterIdleState(this);

        initEventHandlers();
    }

    public double getHeatingPerHour() {
        return HEATING_PER_HOUR;
    }

    private void lowTemperatureEventHandler(AEvent e) {
        if (this.state instanceof HeaterIdleState) {
            changeState(new HeaterUsingState(this));
        }
    }

    private void normalTemperatureEventHandler(AEvent e) {
        if (this.state instanceof HeaterUsingState) {
            changeState(new HeaterIdleState(this));
        }
    }

    private void initEventHandlers() {
        SHSystem.getInstance().getEventDispatcher().addEventHandler(LowTemperature.class, room.toString(), this::lowTemperatureEventHandler);
        SHSystem.getInstance().getEventDispatcher().addEventHandler(NormalTemperature.class, room.toString(), this::normalTemperatureEventHandler);
    }

    public void on() {
        if (this.state instanceof HeaterOffState) {
            changeState(new HeaterIdleState(this));
        }
    }

    public void off() {
        changeState(new HeaterOffState(this));
    }

    @Override
    public void use(Adult person) {

    }

    @Override
    public void unUse(Adult person) {

    }

    @Override
    public void update(long time) {
        state.update(time);
    }
}
