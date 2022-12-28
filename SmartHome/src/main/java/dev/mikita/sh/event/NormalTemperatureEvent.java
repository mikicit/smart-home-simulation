package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

public class NormalTemperatureEvent extends AEvent {
    public NormalTemperatureEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
