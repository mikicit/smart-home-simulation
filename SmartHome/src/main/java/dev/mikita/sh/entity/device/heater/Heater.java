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
import dev.mikita.sh.entity.sensor.ASensor;
import dev.mikita.sh.entity.sensor.HeatSensor;
import dev.mikita.sh.event.LowTemperatureEvent;
import dev.mikita.sh.event.NormalTemperatureEvent;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing the Heater.
 */
public class Heater extends ADevice {
    // Constants
    private final double HEATING_PER_HOUR = 5;

    /**
     * Instantiates a new Heater.
     *
     * @param room the room
     * @param name the name
     */
    public Heater(Room room, String name) {
        super(room, name);
        this.state = new HeaterIdleState(this);
        this.fixingTimeInHours = 0.5;
        this.operatingTimeInHours = 4500;
        this.usageTimeInHour = 0;
        this.doc = new Documentation(this, this.fixingTimeInHours);

        initEventHandlers();
    }

    /**
     * Gets heating per hour.
     *
     * @return the heating per hour
     */
    public double getHeatingPerHour() {
        return HEATING_PER_HOUR;
    }

    /**
     * Turns the heater on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new HeaterIdleState(this));
        }
    }

    /**
     * Turns the heater off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new HeaterOffState(this));
        }
    }

    /**
     * Use the heater.
     * @param inhabitant inhabitant that uses object
     */
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

    /**
     * UnUse the heater.
     * @param inhabitant inhabitant that unUses object
     */
    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            ((Adult) inhabitant).unUseObject(this);
            changeState(new HeaterIdleState(this));

            List<ASensor> heatSensors = room.getSensors().stream()
                    .filter(e -> e instanceof HeatSensor)
                    .collect(Collectors.toList());

            for (ASensor heatSensor : heatSensors) {
                heatSensor.resetState();
            }
        }
    }

    /**
     * Fix the heater.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new HeaterFixingState(this));
        }
    }

    /**
     * Complete fixing the heater.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new HeaterIdleState(this));
        }
    }

    /**
     * To break the heater.
     */
    @Override
    public void toBreak() {
        changeState(new HeaterBrokenState(this));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }

    /**
     * Initiates event handlers.
     */
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
