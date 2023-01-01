package dev.mikita.sh.entity.inhabitant.pet.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;

import java.util.logging.Logger;

/**
 * Class representing pet sleeping state
 */
public class PetSleepingState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(PetSleepingState.class.getName());

    public PetSleepingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("Pet \"%s\" is sleeping now :3 [%s]",
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

        if (simulationTime.getHour() < 23 && simulationTime.getHour() >= 7) {
            inhabitant.changeState(new PetAwakeState(inhabitant));

            log.info(String.format("Pet \"%s\" woke up [%s]",
                    inhabitant.getName(),
                    SHSystem.getInstance().getSimulation().getFormattedTime()));
        }

    }
}
