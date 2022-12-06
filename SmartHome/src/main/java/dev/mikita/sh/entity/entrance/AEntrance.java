package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.core.ITimeTracker;

public abstract class AEntrance implements ITimeTracker {
    protected enum EntranceState {
        OPEN,
        CLOSE
    }

    protected EntranceState state = EntranceState.CLOSE;

    protected void switchState() {
        state = state == EntranceState.OPEN ? EntranceState.CLOSE : EntranceState.OPEN;
    }
}
