package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

/**
 * Abstract class representing an item.
 */
public abstract class AItem implements IUsableObject {
    // Constants
    protected int operatingTimeInHours = 0;
    protected double usageTimeInHour = 0;
    protected int hungerPerHour = 0;
    protected int leisurePerHour = 0;

    // References
    private final String name;
    private final Room room;

    // State
    private boolean isUsing = false;

    public AItem(Room room, String name) {
        this.room = room;
        this.name = name;
    }

    /**
     * Returns the room in which item is located.
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Returns the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the time that item is supposed to be used.
     * @return time
     */
    @Override
    public double getUsageTimeInHour() {
        return usageTimeInHour;
    }

    /**
     * Uses the item.
     * @param inhabitant inhabitant that uses object
     */
    @Override
    public void use(AInhabitant inhabitant) {
        ((Adult) inhabitant).useObject(this);
        isUsing = true;
    }

    /**
     * UnUses the item.
     * @param inhabitant inhabitant that unUses object
     */
    @Override
    public void unUse(AInhabitant inhabitant) {
        ((Adult) inhabitant).unUseObject(this);
        isUsing = false;
    }

    /**
     * Checks if item is being used.
     * @return true if being used
     */
    @Override
    public boolean isUsing() {
        return isUsing;
    }
}
