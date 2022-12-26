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

        // Indicators
        inhabitant.setHungerIndicator(inhabitant.getHungerIndicator() - (inhabitant.getHungerPerHour() / 3600D * 1000000000) * time);
        inhabitant.setLeisureIndicator(inhabitant.getLeisureIndicator() - (inhabitant.getLeisurePerHour() / 3600D * 1000000000) * time);

        inhabitant.setHungerIndicator(inhabitant.getHungerIndicator() + (inhabitant.getUsableObject().getHungerPerHour() / 3600D * 1000000000) * time);
        inhabitant.setLeisureIndicator(inhabitant.getLeisureIndicator() + (inhabitant.getUsableObject().getLeisurePerHour() / 3600D * 1000000000) * time);

        // UnUse
        if (this.time > inhabitant.getUsableObject().getUsageTimeInHour() / 3600F * 1000000000) {
            inhabitant.getUsableObject().unUse(inhabitant);
            inhabitant.changeState(new AdultWaitingState(inhabitant));
        }
    }
}
