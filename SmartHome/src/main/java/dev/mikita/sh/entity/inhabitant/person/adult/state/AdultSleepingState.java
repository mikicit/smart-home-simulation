package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;

import java.util.logging.Logger;

/**
 * Class representing adult sleeping state.
 */
public class AdultSleepingState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(AdultSleepingState.class.getName());

    //Constants
    private final int START_WAKE_TIME = 23;
    private final int END_WAKE_TIME = 7;

    public AdultSleepingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("%s \"%s\" went to bed [%s]",
                inhabitant.getClass().getSimpleName(),
                inhabitant.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();

        if (simulationTime.getHour() < START_WAKE_TIME && simulationTime.getHour() >= END_WAKE_TIME) {
            inhabitant.changeState(new AdultWaitingState(inhabitant));
            log.info(String.format("%s \"%s\" woke up [%s]",
                    inhabitant.getClass().getSimpleName(),
                    inhabitant.getName(),
                    SHSystem.getInstance().getSimulation().getFormattedTime()));
        }
    }
}
