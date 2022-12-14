package dev.mikita.sh.entity.inhabitant.pet.state;

import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;

public class PetSleepingState extends AInhabitantState {
    public PetSleepingState(AInhabitant inhabitant) {
        super(inhabitant);
    }

    @Override
    public void update(long time) {

    }
}
