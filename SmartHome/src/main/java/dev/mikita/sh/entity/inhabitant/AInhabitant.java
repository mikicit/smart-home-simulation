package dev.mikita.sh.entity.inhabitant;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.simulation.ITimeTracker;
import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.location.Room;
import java.util.logging.Logger;

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

    public Room getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    public void moveTo(Room room) {
        log.info(String.format("%s \"%s\" moved from room \"%s\" to room \"%s\" [%s]",
                this.getClass().getSimpleName(),
                this.getName(),
                this.getRoom().getName(),
                room.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));

        this.room = room;
    }

    public double getDeviceBreakingChance() {
        return deviceBreakingChance;
    }

    public IUsableObject getUsableObject() {
        return usingObject;
    }

    public void changeState(AInhabitantState state) {
        this.state = state;
    }
}
