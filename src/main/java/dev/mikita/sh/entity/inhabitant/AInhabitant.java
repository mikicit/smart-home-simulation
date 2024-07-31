package dev.mikita.sh.entity.inhabitant;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.simulation.ITimeTracker;
import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.location.Room;
import java.util.logging.Logger;

/**
 * Abstract class representing inhabitant
 */
public abstract class AInhabitant implements ITimeTracker, IEventSource {
    // Logger
    private static final Logger log = Logger.getLogger(AInhabitant.class.getName());

    protected final String name;
    protected Room room;
    protected AInhabitantState state;
    protected IUsableObject usingObject = null;
    protected long time = 0;

    // Constants
    protected double deviceBreakingChance = 0;

    public AInhabitant(Room room, String name) {
        this.room = room;
        this.name = name;

        // Init
        SHSystem.getInstance().getSimulation().subscribe(this);
    }

    /**
     * Returns the room in which inhabitant is located
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Returns the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Moves to another room
     * @param room the room
     */
    public void moveTo(Room room) {
        log.info(String.format("%s \"%s\" moved from room \"%s\" to room \"%s\" [%s]",
                this.getClass().getSimpleName(),
                this.getName(),
                this.getRoom().getName(),
                room.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));

        this.room = room;
    }

    /**
     * Returns the chance of breaking the device
     * @return chance
     */
    public double getDeviceBreakingChance() {
        return deviceBreakingChance;
    }

    /**
     * Returns the using object
     * @return device/item
     */
    public IUsableObject getUsableObject() {
        return usingObject;
    }

    /**
     * Changes the state
     * @param state the state to set
     */
    public void changeState(AInhabitantState state) {
        this.state = state;
    }

    /**
     * Returns the current state
     * @return state
     */
    public AInhabitantState getState() {
        return this.state;
    }
}
