package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;

import java.util.logging.Logger;

/**
 * Class representing adult working state
 */
public class AdultWorkingState extends AInhabitantState  {
    // Logger
    private static final Logger log = Logger.getLogger(AdultWorkingState.class.getName());

    public AdultWorkingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("%s \"%s\" went to work [%s]",
                inhabitant.getClass().getSimpleName(),
                inhabitant.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;

        Simulation simulationTime = SHSystem.getInstance().getSimulation();

        // Go home
        if (simulationTime.getHour() < 10 || simulationTime.getHour() >= 17) {
            inhabitant.changeState(new AdultWaitingState(inhabitant));

            log.info(String.format("%s \"%s\" arrived home from work [%s]",
                    inhabitant.getClass().getSimpleName(),
                    inhabitant.getName(),
                    SHSystem.getInstance().getSimulation().getFormattedTime()));
        }
    }
}
