package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.event.ILocation;

public class NormalTemperature extends AEvent {
    public NormalTemperature(IEventSource source, ILocation location) {
        super(source, location);
    }
}
