package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.UsableObject;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public abstract class AItem implements UsableObject {
    private boolean isUsing = false;
    private final String name;
    private final Room room;

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
    public void use(Adult adult) {
        adult.useObject(this);
        isUsing = true;
    }

    @Override
    public void unUse(Adult adult) {
        adult.unUseObject();
        isUsing = false;
    }
}
