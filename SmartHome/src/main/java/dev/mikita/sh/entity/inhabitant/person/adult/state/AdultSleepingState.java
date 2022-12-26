package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.time.SimulationTime;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;

import java.util.logging.Logger;

public class AdultSleepingState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(AdultSleepingState.class.getName());

    public AdultSleepingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("Person \"%s\" went to bed [%s]",
                inhabitant.getName(),
                SHSystem.getInstance().getTimer().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        SimulationTime simulationTime = SHSystem.getInstance().getTimer();

        if (simulationTime.getHour() < 23 && simulationTime.getHour() >= 7) {
            inhabitant.changeState(new AdultWaitingState(inhabitant));
            log.info(String.format("Person \"%s\" waked up [%s]",
                    inhabitant.getName(),
                    SHSystem.getInstance().getTimer().getFormattedTime()));
        }
    }
}
