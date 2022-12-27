package dev.mikita.sh.entity.device.heater;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.BaseTestHandler;
import dev.mikita.sh.core.event.BaseTestHandlerA;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.heater.state.HeaterIdleState;
import dev.mikita.sh.entity.device.heater.state.HeaterOffState;
import dev.mikita.sh.entity.device.heater.state.HeaterUsingState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.LowTemperatureEvent;
import dev.mikita.sh.event.NormalTemperatureEvent;

public class Heater extends ADevice {
    // Constants
    private final double HEATING_PER_HOUR = 5;

    public Heater(Room room, String name) {
        super(room, name);
        this.state = new HeaterIdleState(this);
        this.operatingTimeInHours = 1350;
        this.usageTimeInHour = 0;
        this.hungerPerHour = 0;
        this.leisurePerHour = 0;

        initEventHandlers();
    }

    public double getHeatingPerHour() {
        return HEATING_PER_HOUR;
    }

//    private void lowTemperatureEventHandler(AEvent e) {
//        if (this.state instanceof HeaterIdleState) {
//            changeState(new HeaterUsingState(this));
//        }
//    }
//
//    private void normalTemperatureEventHandler(AEvent e) {
//        if (this.state instanceof HeaterUsingState) {
//            changeState(new HeaterIdleState(this));
//        }
//    }

    private void initEventHandlers() {
        SHSystem.getInstance().getEventDispatcher().addEventHandler(LowTemperatureEvent.class, room.getName(), new BaseTestHandlerA() {
            @Override
            public void handle(AEvent e) {
                if (Heater.this.state instanceof HeaterIdleState) {
                    Heater.this.changeState(new HeaterUsingState(Heater.this));
                }

                if (nextHandler != null) {
                    nextHandler.handle(e);
                }
            }
        });
        SHSystem.getInstance().getEventDispatcher().addEventHandler(NormalTemperatureEvent.class, room.getName(), new BaseTestHandlerA() {
            @Override
            public void handle(AEvent e) {
                if (Heater.this.state instanceof HeaterUsingState) {
                    Heater.this.changeState(new HeaterIdleState(Heater.this));
                }

                if (nextHandler != null) {
                    nextHandler.handle(e);
                }
            }
        });
    }

//    public void on() {
//        if (this.state instanceof HeaterOffState) {
//            changeState(new HeaterIdleState(this));
//        }
//    }
//
//    public void off() {
//        changeState(new HeaterOffState(this));
//    }

    @Override
    public void use(AInhabitant inhabitant) {

    }

    @Override
    public void unUse(AInhabitant inhabitant) {

    }

    @Override
    public void fix(Adult person) {

    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
