package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

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

    public Room getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getOperatingTimeInHours() {
        return operatingTimeInHours;
    }

    @Override
    public double getUsageTimeInHour() {
        return usageTimeInHour;
    }

    @Override
    public void use(AInhabitant inhabitant) {
        ((Adult) inhabitant).useObject(this);
        isUsing = true;
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        ((Adult) inhabitant).unUseObject(this);
        isUsing = false;
    }

    @Override
    public boolean isOn() {
        return false;
    }

    @Override
    public boolean isOff() {
        return false;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public boolean isFixing() {
        return false;
    }
}
