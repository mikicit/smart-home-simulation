package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.simulation.ITimeTracker;

public abstract class ASensor implements ITimeTracker, IEventSource {
    protected long time = 0;

    public ASensor() {
        // Init
        SHSystem.getInstance().getSimulation().subscribe(this);
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    protected abstract void switchState();
    public abstract void resetState();
}
