package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.location.Room;

public abstract class AItem implements IUsableObject {
    private boolean isUsing = false;
    private final String name;
    private final Room room;

    // Constants
    protected int operatingTimeInHours = 0;
    protected double usageTimeInHour = 0;
    protected int hungerPerHour = 0;
    protected int leisurePerHour = 0;

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
    public int getHungerPerHour() {
        return hungerPerHour;
    }

    @Override
    public int getLeisurePerHour() {
        return leisurePerHour;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    @Override
    public void use(AInhabitant inhabitant) {
        inhabitant.useObject(this);
        isUsing = true;
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        inhabitant.unUseObject(this);
        isUsing = false;
    }
}
