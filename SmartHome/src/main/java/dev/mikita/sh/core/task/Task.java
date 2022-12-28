package dev.mikita.sh.core.task;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.event.DeviceIsBrokenEvent;

public class Task {
    private AEvent event;

    public Task(AEvent event) {
        this.event = event;
    }

    public void apply(Adult adult) {
        if (event instanceof DeviceIsBrokenEvent) {
            ((ADevice) event.getSource()).fix(adult);
        }
    }
}
