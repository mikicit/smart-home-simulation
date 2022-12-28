package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.pet.state.PetAwakeState;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.BoredPetEvent;
import dev.mikita.sh.event.HungryChildEvent;
import dev.mikita.sh.event.HungryPetEvent;

public class Dragon extends APet {
    public Dragon(Room room, String name) {
        super(room, name);
    }

    @Override
    public void update(long time) {
        state.update(time);

        if (this.getHungerIndicator() == 0 && !this.dispatchedHungerEvent) {
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new HungryPetEvent(this, room), room.getName());
            dispatchedHungerEvent = true;
        }
        if (this.getLeisureIndicator() == 0 && !this.dispatchedBoredEvent) {
            SHSystem.getInstance().getEventDispatcher().dispatchEvent(new BoredPetEvent(this, room), room.getName());
            dispatchedBoredEvent = true;
        }
    }

    @Override
    public void changeState(AInhabitantState state) {
        this.state = state;
    }

    public void resetState() {
        if (this.dispatchedHungerEvent)
            this.dispatchedHungerEvent = false;
        if (this.dispatchedBoredEvent)
            this.dispatchedBoredEvent = false;
    }
}
