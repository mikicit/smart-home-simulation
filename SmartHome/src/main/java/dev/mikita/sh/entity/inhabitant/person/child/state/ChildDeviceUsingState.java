package dev.mikita.sh.entity.inhabitant.person.child.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.PersonFactory;

import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChildDeviceUsingState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(ChildDeviceUsingState.class.getName());

    public ChildDeviceUsingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("Child \"%s\" is trying to use object \"%s\" [%s]",
                inhabitant.getName(),
                inhabitant.getUsableObject().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;

        // Indicators
        inhabitant.setHungerIndicator(inhabitant.getHungerIndicator()
                - (inhabitant.getHungerPerHour() / 3600D * 1000000000) * time);
        inhabitant.setLeisureIndicator(inhabitant.getLeisureIndicator()
                - (inhabitant.getLeisurePerHour() / 3600D * 1000000000) * time);

        inhabitant.setLeisureIndicator(inhabitant.getLeisureIndicator()
                + (inhabitant.getUsableObject().getLeisurePerHour() / 3600D * 1000000000) * time);


        if (Math.random() >= inhabitant.getDeviceBreakingChance()) {
            log.info(String.format("Child \"%s\" broke the device \"%s\" :( [%s]",
                    inhabitant.getName(),
                    inhabitant.getUsableObject().getName(),
                    SHSystem.getInstance().getSimulation().getFormattedTime()));

            ((ADevice) inhabitant.getUsableObject()).toBeBroken(inhabitant);
        }
        else {
            log.info(String.format("Child \"%s\" didn't break the device \"%s\", HOORAY :D [%s]",
                    inhabitant.getName(),
                    inhabitant.getUsableObject().getName(),
                    SHSystem.getInstance().getSimulation().getFormattedTime()));

        }
        inhabitant.getUsableObject().unUse(inhabitant);
        inhabitant.changeState(new ChildAwakeState(inhabitant));
    }
}
