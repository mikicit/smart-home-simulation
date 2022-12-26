package dev.mikita.sh.core.simulation;

import java.io.IOException;

public interface ITimeTracker {
    void update(long time) throws IOException;
}
