package dev.mikita.sh.entity.inhabitant;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.time.ITimeTracker;
import dev.mikita.sh.entity.UsableObject;
import dev.mikita.sh.entity.location.Room;

import java.util.logging.Logger;

import static java.lang.Double.max;

public abstract class AInhabitant implements ITimeTracker, IEventSource {
    // Logger
    private static final Logger log = Logger.getLogger(AInhabitant.class.getName());

    protected String name;
    protected Room room;
    protected AInhabitantState state;
    protected UsableObject usingObject = null;

    // Constants
    protected int hungerPerHour = 0;
    protected int leisurePerHour = 0;
    protected int deviceBreakingChance = 0;

    // Indicators
    protected double hungerIndicator = 0;
    protected double leisureIndicator = 0;

    public AInhabitant(Room room, String name) {
        this.room = room;
        this.name = name;

        // Init
        SHSystem.getInstance().getTimer().subscribe(this);
    }

    public Room getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    public void moveTo(Room room) {
        log.info(String.format("\"%s\" moved from room \"%s\" to room \"%s\" [%s]",
                this.getName(),
                this.getRoom(),
                room.getName(),
                SHSystem.getInstance().getTimer().getFormattedTime()));

        this.room = room;
    }

    public int getHungerPerHour() {
        return hungerPerHour;
    }

    public int getLeisurePerHour() {
        return leisurePerHour;
    }

    public int getDeviceBreakingChance() {
        return deviceBreakingChance;
    }

    public void setHungerIndicator(double value) {
        hungerIndicator = max(0, value);
    }

    public void setLeisureIndicator(double value) {
        leisureIndicator = max(0, value);
    }

    public double getHungerIndicator() {
        return hungerIndicator;
    }

    public double getLeisureIndicator() {
        return leisureIndicator;
    }

    public UsableObject getUsableObject() {
        return usingObject;
    }

    public abstract void changeState(AInhabitantState state);
    public abstract void useObject(UsableObject object);
    public abstract void unUseObject(UsableObject object);
}
