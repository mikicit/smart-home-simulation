package dev.mikita.sh.entity.inhabitant.person.adult.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing adult device fixing state.
 */
@Slf4j
public class AdultDeviceFixingState extends AInhabitantState {
    public AdultDeviceFixingState(AInhabitant inhabitant) {
        super(inhabitant);

        log.info(String.format("%s \"%s\" started fixing object \"%s\" [%s]",
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

        if (this.time > ((ADevice) inhabitant.getUsableObject()).getFixingTimeInHours() * 3600L * 1000000000L) {
            ((ADevice) inhabitant.getUsableObject()).completeFixing((Adult) inhabitant);
            inhabitant.changeState(new AdultWaitingState(inhabitant));
        }
    }
}
