package dev.mikita.sh.entity.inhabitant.person.child;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildAwakeState;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.HungryChildEvent;
import dev.mikita.sh.event.PoopedChildEvent;

import java.util.logging.Logger;

/**
 * Class representing the child.
 */
public class Child extends APerson {
    // Logger
    private static final Logger log = Logger.getLogger(Child.class.getName());

    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 4;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 48;

    // State
    private long timeFromLastDispatchedHungerEvent = 0;
    private long timeFromLastDispatchedPoopedEvent = 0;
    private boolean dispatchedHungerEvent = false;
    private boolean dispatchedPoopedEvent = false;
    private double triggeredHungerTimeInHours;
    private double triggeredPoopedTimeInHours;

    public Child(Room room, String name, PersonGender gender) {
        super(room, name);
        this.state = new ChildAwakeState(this);
        this.gender = gender;
        this.triggeredHungerTimeInHours = calculateTriggeredTime();
        this.triggeredPoopedTimeInHours = calculateTriggeredTime();
        this.deviceBreakingChance = 0.3;
    }

    /**
     * Calculates event triggered time.
     * @return time
     */
    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    /**
     * Feeds the child.
     * @param adult the adult
     */
    public void feed(Adult adult) {
        adult.feedChild(this);
        dispatchedHungerEvent = false;
    }

    /**
     * Changes child's diaper.
     * @param adult the adult
     */
    public void changeDiaper(Adult adult) {
        adult.changeDiapers(this);
        dispatchedPoopedEvent = false;
    }

    /**
     * Checks pooped event.
     * @return true if dispatched
     */
    public boolean getDispatchedPoopedEvent() {
        return dispatchedPoopedEvent;
    }

    /**
     * Checks hungry event.
     * @return true if dispatched
     */
    public boolean getDispatchedHungerEvent() {
        return dispatchedHungerEvent;
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedHungerEvent += time;
        this.timeFromLastDispatchedPoopedEvent += time;

        if (!dispatchedHungerEvent && timeFromLastDispatchedHungerEvent >= triggeredHungerTimeInHours * 3600 * 1000000000) {
            dispatchedHungerEvent = true;
            timeFromLastDispatchedHungerEvent = 0;
            triggeredHungerTimeInHours = calculateTriggeredTime();
            changeState(new ChildAwakeState(this));
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new HungryChildEvent(this, room), "global");
        }

        if (!dispatchedPoopedEvent && timeFromLastDispatchedPoopedEvent >= triggeredPoopedTimeInHours * 3600 * 1000000000) {
            dispatchedPoopedEvent = true;
            timeFromLastDispatchedPoopedEvent = 0;
            triggeredPoopedTimeInHours = calculateTriggeredTime();
            changeState(new ChildAwakeState(this));
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new PoopedChildEvent(this, room), "global");
        }

        this.state.update(time);
    }
}
