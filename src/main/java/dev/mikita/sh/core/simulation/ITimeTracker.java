package dev.mikita.sh.core.simulation;

import java.io.IOException;

/**
 * Interface representing time.
 */
public interface ITimeTracker {
    void update(long time) throws IOException;
}
