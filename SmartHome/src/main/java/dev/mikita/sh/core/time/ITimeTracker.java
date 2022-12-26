package dev.mikita.sh.core.time;

import java.io.IOException;

public interface ITimeTracker {
    void update(long time) throws IOException;
}
