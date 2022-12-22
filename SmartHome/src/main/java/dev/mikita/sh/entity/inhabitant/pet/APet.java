package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.entity.UsableObject;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.pet.state.PetAwakeState;
import dev.mikita.sh.entity.location.Room;

public abstract class APet extends AInhabitant  {
    public APet(Room room, String name) {
        super(room, name);
        this.state = new PetAwakeState(this);
        this.hungerIndicator = 100;
        this.leisureIndicator = 100;
        this.hungerPerHour = 10;
        this.leisurePerHour = 5;
        this.deviceBreakingChance = 20;
    }

    @Override
    public void useObject(UsableObject object) {}

    @Override
    public void unUseObject(UsableObject object) {}
}
