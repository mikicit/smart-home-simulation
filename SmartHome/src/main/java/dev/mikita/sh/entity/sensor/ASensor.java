package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.ITimeTracker;

public abstract class ASensor implements ITimeTracker {
    protected abstract void switchState();
}
