package dev.mikita.sh.entity.inhabitant.person.child.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultSleepingState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;

import java.util.logging.Logger;

public class ChildSleepingState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(ChildSleepingState.class.getName());

    public ChildSleepingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("Child \"%s\" went to bed [%s]",
                inhabitant.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();

        if (simulationTime.getHour() < 21 && simulationTime.getHour() >= 8) {
            inhabitant.changeState(new ChildAwakeState(inhabitant));
            log.info(String.format("Child \"%s\" woke up [%s]",
                    inhabitant.getName(),
                    SHSystem.getInstance().getSimulation().getFormattedTime()));
        }
    }
}
