package dev.mikita.sh.entity.inhabitant.person.child;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildAwakeState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildDeviceUsingState;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.HungryChildEvent;
import dev.mikita.sh.event.PoopedChildEvent;
import dev.mikita.sh.event.SmokeInRoomEvent;

import java.util.logging.Logger;

public class Child extends APerson {
    // Logger
    private static final Logger log = Logger.getLogger(Child.class.getName());

    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 2;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 24;

    // Dispatched events flags
    private boolean dispatchedHungerEvent = false;
    private boolean dispatchedPoopedEvent = false;

    private double triggeredTimeInHours;

    public Child(Room room, String name) {
        super(room, name);
        this.state = new ChildAwakeState(this);
        this.triggeredTimeInHours = calculateTriggeredTime();
        this.hungerIndicator = 100;
        this.leisureIndicator = 100;
        this.hungerPerHour = 10;
        this.leisurePerHour = 2;
        this.deviceBreakingChance = 0.5;
    }

    public void beFed(Adult adult) {
        adult.feedChild(this);
    }

    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    @Override
    public void update(long time) {
        this.time += time;
        this.state.update(time);

        if (hungerIndicator == 0 && !dispatchedHungerEvent) {
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new HungryChildEvent(this, room), room.getName());
            dispatchedHungerEvent = true;
        }
        if (this.time >= triggeredTimeInHours * 3600L * 1000000000L && !dispatchedPoopedEvent) {
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new PoopedChildEvent(this, room), room.getName());
            dispatchedPoopedEvent = true;
        }
    }

    @Override
    public void changeState(AInhabitantState state) {
        this.state = state;
    }

    @Override
    public void useObject(IUsableObject object) {
            this.usingObject = object;
            changeState(new ChildDeviceUsingState(this));

//            log.info(String.format("Device \"%s\" is broken, cannot be used! [%s]",
//                    object.getName(),
//                    SHSystem.getInstance().getSimulation().getFormattedTime()));

    }

    @Override
    public void unUseObject(IUsableObject object) {
        this.usingObject = null;
        changeState(new ChildAwakeState(this));
    }

    @Override
    public void toBreakDevice(ADevice device) {
        this.usingObject = device;
        changeState(new ChildAwakeState(this));
    }

    public void resetState() {
        if (dispatchedHungerEvent)
            dispatchedHungerEvent = false;
        if (dispatchedPoopedEvent) {
            this.time = 0;
            triggeredTimeInHours = calculateTriggeredTime();
            dispatchedPoopedEvent = false;
        }
    }
}
