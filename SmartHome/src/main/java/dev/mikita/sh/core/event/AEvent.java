package dev.mikita.sh.core.event;

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
