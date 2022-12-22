package dev.mikita.sh.core.time;

import java.util.ArrayList;
import java.util.List;

public class SimulationTime {
    private long time = 0;
    private int sec, min, hour = 0;
    private int day = 1;

    private final List<ITimeTracker> subscribers = new ArrayList<>();

    public void subscribe(ITimeTracker subscriber) {
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(ITimeTracker subscriber) {
        this.subscribers.remove(subscriber);
    }

    public void update(long time) {
        this.time += time;

        if (this.time > 1000000000) {
            this.time = 0;
            sec++;
        }

        if (sec >= 60) {
            sec = 0;
            min++;
        }

        if (min >= 60) {
            min = 0;
            hour++;
        }

        if (hour >= 24) {
            hour = 0;
            day++;
        }

        for (ITimeTracker subscriber : this.subscribers) {
            subscriber.update(time);
        }
    }

    public long getTime() {
        return time;
    }

    public String getFormattedTime() {
        return String.format("Day: %s, Hour: %s, Min: %s", day, hour, min);
    }

    public int getMin() {
        return min;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }
}
