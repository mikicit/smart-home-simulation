package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.event.ILocation;

public class SmokeInRoomEvent extends AEvent {
    public SmokeInRoomEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
