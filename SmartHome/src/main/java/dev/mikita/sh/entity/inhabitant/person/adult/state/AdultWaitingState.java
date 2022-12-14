package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;

public class AdultWaitingState extends AInhabitantState {
    public AdultWaitingState(AInhabitant inhabitant) {
        super(inhabitant);
    }

    @Override
    public void update(long time) {

    }
}
