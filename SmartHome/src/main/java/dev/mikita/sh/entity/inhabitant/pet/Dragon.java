package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.pet.state.PetAwakeState;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.BoredPetEvent;
import dev.mikita.sh.event.HungryPetEvent;

/**
 * Class representing a dragon
 */
public class Dragon extends APet {
    // Constants
    private final double MIN_TRIGGERED_TIME_IN_HOURS = 5;
    private final double MAX_TRIGGERED_TIME_IN_HOURS = 25;

    // State
    private long timeFromLastDispatchedHungerEvent = 0;
    private long timeFromLastDispatchedPlayedEvent = 0;
    private boolean dispatchedHungerEvent = false;
    private boolean dispatchedPlayedEvent = false;
    private double triggeredHungerTimeInHours;
    private double triggeredPlayedTimeInHours;

    public Dragon(Room room, String name) {
        super(room, name);
        this.triggeredHungerTimeInHours = calculateTriggeredTime();
        this.triggeredPlayedTimeInHours = calculateTriggeredTime();
    }

    /**
     * Calculates event triggered time
     * @return time
     */
    private double calculateTriggeredTime() {
        return Math.random() * (MAX_TRIGGERED_TIME_IN_HOURS - MIN_TRIGGERED_TIME_IN_HOURS + 1) + MIN_TRIGGERED_TIME_IN_HOURS;
    }

    /**
     * Feeds the dragon
     * @param adult the adult
     */
    public void feed(Adult adult) {
        adult.feedPet(this);
        dispatchedHungerEvent = false;
    }

    /**
     * Plays with dragon
     * @param adult the adult
     */
    public void play(Adult adult) {
        adult.playWithPet(this);
        dispatchedPlayedEvent = false;
    }

    /**
     * Checks bored event
     * @return true if dispatched
     */
    public boolean getDispatchedPlayedEvent() {
        return dispatchedPlayedEvent;
    }

    /**
     * Checks hungry event
     * @return true if dispatched
     */
    public boolean getDispatchedHungerEvent() {
        return dispatchedHungerEvent;
    }

    /**
     * Update
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        this.timeFromLastDispatchedHungerEvent += time;
        this.timeFromLastDispatchedPlayedEvent += time;

        if (!dispatchedHungerEvent && timeFromLastDispatchedHungerEvent >= triggeredHungerTimeInHours * 3600 * 1000000000) {
            dispatchedHungerEvent = true;
            timeFromLastDispatchedHungerEvent = 0;
            triggeredHungerTimeInHours = calculateTriggeredTime();
            changeState(new PetAwakeState(this));
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new HungryPetEvent(this, room), "global");
        }

        if (!dispatchedPlayedEvent && timeFromLastDispatchedPlayedEvent >= triggeredPlayedTimeInHours * 3600 * 1000000000) {
            dispatchedPlayedEvent = true;
            timeFromLastDispatchedPlayedEvent = 0;
            triggeredPlayedTimeInHours = calculateTriggeredTime();
            changeState(new PetAwakeState(this));
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new BoredPetEvent(this, room), "global");
        }

        state.update(time);
    }
}
