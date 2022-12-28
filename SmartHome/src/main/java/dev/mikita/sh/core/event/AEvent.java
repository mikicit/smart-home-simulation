package dev.mikita.sh.core.event;

import dev.mikita.sh.entity.location.ILocation;

public abstract class AEvent {
    private final IEventSource source;
    private final ILocation location;

    public AEvent(IEventSource source, ILocation location) {
        this.source = source;
        this.location = location;
    }

    public IEventSource getSource() {
        return source;
    }

    public ILocation getLocation() {
        return location;
    }
}
