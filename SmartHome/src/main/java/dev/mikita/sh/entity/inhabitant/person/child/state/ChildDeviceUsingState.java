package dev.mikita.sh.entity.inhabitant.person.child.state;

import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;

public class ChildDeviceUsingState extends AInhabitantState {
    public ChildDeviceUsingState(AInhabitant inhabitant) {
        super(inhabitant);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
