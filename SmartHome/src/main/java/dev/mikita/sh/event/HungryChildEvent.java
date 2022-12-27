package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.event.ILocation;

public class HungryChildEvent extends AEvent {
    public HungryChildEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
