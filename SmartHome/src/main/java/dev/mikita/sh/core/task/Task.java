package dev.mikita.sh.core.task;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.person.child.Child;
import dev.mikita.sh.entity.inhabitant.pet.APet;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.event.*;

/**
 * Class representing tasks for event handling.
 * <p>
 * Used in case no one has been able to handle the event at the moment, then it will go into the queue.
 */
public class Task {
    private final AEvent event;

    /**
     * Instantiates a new Task.
     *
     * @param event the event
     */
    public Task(AEvent event) {
        this.event = event;
    }

    /**
     * Applies task to an adult.
     *
     * @param adult adult
     */
    public void apply(Adult adult) {
        if (event instanceof DeviceIsBrokenEvent) {
            ((ADevice) event.getSource()).fix(adult);
        } else if (event instanceof SmokeInRoomEvent) {
            ((Room) event.getLocation()).putOutTheFire(adult);
        } else if (event instanceof WaterInRoomEvent) {
            ((Room) event.getLocation()).fixWaterLeak(adult);
        } else if (event instanceof HungryChildEvent) {
            ((Child) event.getSource()).feed(adult);
        } else if (event instanceof PoopedChildEvent) {
            ((Child) event.getSource()).changeDiaper(adult);
        } else if (event instanceof HungryPetEvent) {
            ((APet) event.getSource()).feed(adult);
        } else if (event instanceof BoredPetEvent) {
            ((APet) event.getSource()).play(adult);
        }
    }
}
