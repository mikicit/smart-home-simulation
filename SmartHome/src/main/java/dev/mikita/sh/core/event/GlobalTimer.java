package dev.mikita.sh.core.event;

import dev.mikita.sh.core.ITimeTracker;

import java.util.List;

public class GlobalTimer {
    private long time;
    private List<ITimeTracker> subscribers;

    public void subscribe(ITimeTracker subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(ITimeTracker subscriber) {
        subscribers.remove(subscriber);
    }

    public void update(long time) {
        for (ITimeTracker subscriber : subscribers) {
            subscriber.update(time);
        }
    }
}
