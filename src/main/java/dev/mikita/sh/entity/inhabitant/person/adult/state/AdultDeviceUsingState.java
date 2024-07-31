package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import java.util.logging.Logger;

/**
 * Class representing adult device using state.
 */
public class AdultDeviceUsingState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(AdultDeviceUsingState.class.getName());

    public AdultDeviceUsingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("%s \"%s\" started using object \"%s\" [%s]",
                inhabitant.getClass().getSimpleName(),
                inhabitant.getName(),
                inhabitant.getUsableObject().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;

        // UnUse
        if (this.time > inhabitant.getUsableObject().getUsageTimeInHour() * 3600L * 1000000000L) {
            inhabitant.getUsableObject().unUse(inhabitant);
            inhabitant.changeState(new AdultWaitingState(inhabitant));
        }
    }
}
