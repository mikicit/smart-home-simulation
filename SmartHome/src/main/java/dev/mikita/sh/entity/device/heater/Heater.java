package dev.mikita.sh.entity.device.heater;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.AEventHandler;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.heater.state.*;
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
        this.fixingTimeInHours = 0.5;
        this.operatingTimeInHours = 4500;
        this.usageTimeInHour = 0;
        this.doc = new Documentation(this, this.fixingTimeInHours);

        initEventHandlers();
    }

    public double getHeatingPerHour() {
        return HEATING_PER_HOUR;
    }

    @Override
    public void on() {
        if (isOff()) {
            changeState(new HeaterIdleState(this));
        }
    }

    @Override
    public void off() {
        if (isOn()) {
            changeState(new HeaterOffState(this));
        }
    }

    @Override
    public void use(AInhabitant inhabitant) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new HeaterBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            ((Adult) inhabitant).useObject(this);
            changeState(new HeaterUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            ((Adult) inhabitant).unUseObject(this);
            changeState(new HeaterIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new HeaterFixingState(this));
        }
    }

    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new HeaterIdleState(this));
        }
    }

    @Override
    public void toBreak() {
        changeState(new HeaterBrokenState(this));
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }

    private void initEventHandlers() {
        SHSystem.getInstance().getEventDispatcher().addEventHandler(LowTemperatureEvent.class, room.getName(), new AEventHandler() {
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

        SHSystem.getInstance().getEventDispatcher().addEventHandler(NormalTemperatureEvent.class, room.getName(), new AEventHandler() {
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
}
