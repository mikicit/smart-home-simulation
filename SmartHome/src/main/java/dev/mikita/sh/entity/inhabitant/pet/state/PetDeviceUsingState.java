package dev.mikita.sh.entity.inhabitant.pet.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildAwakeState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildDeviceUsingState;

import java.util.logging.Logger;

public class PetDeviceUsingState extends AInhabitantState {
    // Logger
    private static final Logger log = Logger.getLogger(PetDeviceUsingState.class.getName());

    public PetDeviceUsingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("Pet \"%s\" is playing with object \"%s\" [%s]",
                inhabitant.getName(),
                inhabitant.getUsableObject().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
//        this.time += time;
//
//        // Indicators
//        inhabitant.setHungerIndicator(inhabitant.getHungerIndicator()
//                - (inhabitant.getHungerPerHour() / 3600D * 1000000000) * time);
//        inhabitant.setLeisureIndicator(inhabitant.getLeisureIndicator()
//                - (inhabitant.getLeisurePerHour() / 3600D * 1000000000) * time);
//
//        inhabitant.setLeisureIndicator(inhabitant.getLeisureIndicator()
//                + (inhabitant.getUsableObject().getLeisurePerHour() / 3600D * 1000000000) * time);
//
//
//        if (Math.random() >= inhabitant.getDeviceBreakingChance()) {
//            log.info(String.format("Pet \"%s\" broke the device \"%s\" while playing with it :( [%s]",
//                    inhabitant.getName(),
//                    inhabitant.getUsableObject().getName(),
//                    SHSystem.getInstance().getSimulation().getFormattedTime()));
//
//            ((ADevice) inhabitant.getUsableObject()).toBeBroken(inhabitant);
//        }
//        else {
//            log.info(String.format("Pet \"%s\" didn't break the device \"%s\", HOORAY :D [%s]",
//                    inhabitant.getName(),
//                    inhabitant.getUsableObject().getName(),
//                    SHSystem.getInstance().getSimulation().getFormattedTime()));
//
//        }
//        inhabitant.getUsableObject().unUse(inhabitant);
//        inhabitant.changeState(new PetAwakeState(inhabitant));
    }
}
