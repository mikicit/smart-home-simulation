package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;

import java.util.logging.Logger;

public class AdultSleepingState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(AdultSleepingState.class.getName());

    public AdultSleepingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("%s \"%s\" went to bed [%s]",
                inhabitant.getClass().getSimpleName(),
                inhabitant.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();

        if (simulationTime.getHour() < 23 && simulationTime.getHour() >= 7) {
            inhabitant.changeState(new AdultWaitingState(inhabitant));
            log.info(String.format("%s \"%s\" woke up [%s]",
                    inhabitant.getClass().getSimpleName(),
                    inhabitant.getName(),
                    SHSystem.getInstance().getSimulation().getFormattedTime()));
        }
    }
}
