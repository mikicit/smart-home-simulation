package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.simulation.ITimeTracker;

public abstract class ASensor implements ITimeTracker, IEventSource {
    public ASensor() {
        // Init
        SHSystem.getInstance().getSimulation().subscribe(this);
    }

    protected abstract void switchState();
    protected abstract void resetState();
}
