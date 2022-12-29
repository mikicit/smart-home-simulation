package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import java.util.logging.Logger;

public class AdultDeviceUsingState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(AdultDeviceUsingState.class.getName());

    public AdultDeviceUsingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("Person \"%s\" started using object \"%s\" [%s]",
                inhabitant.getName(),
                inhabitant.getUsableObject().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

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
