package dev.mikita.sh.core.simulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private long time = 0;
    private long tempTime = 0;
    private int sec, min, hour = 0;
    private int day = 1;
    private final List<ITimeTracker> subscribers = new ArrayList<>();
    private boolean exit = false;

    private void update(long time) throws IOException {
        this.time += time;
        this.tempTime += time;

        if (this.tempTime > 1000000000) {
            sec += this.tempTime / 1000000000;
            this.tempTime = this.tempTime % 1000000000;
        }

        if (sec >= 60) {
            min += sec / 60;
            sec = sec % 60;
        }

        if (min >= 60) {
            hour += min / 60;
            min = min % 60;
        }

        if (hour >= 24) {
            day += hour / 24;
            hour = hour % 24;
        }

        for (ITimeTracker subscriber : this.subscribers) {
            subscriber.update(time);
        }
    }

    public void start(int speed, long timeToSimulate) {
        exit = false;

        new Thread(() -> {
            long lastUpdate = System.nanoTime();

            while (!exit && timeToSimulate > time) {
                long currentTime = System.nanoTime();

                if (currentTime - lastUpdate > (1000000000 / 60)) {
                    long delta = speed * (currentTime - lastUpdate);
                    lastUpdate = currentTime;

                    try {
                        update(delta);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public void stop() {
        exit = true;
    }

    public long getTime() {
        return time;
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

    public String getFormattedTime() {
        return String.format("Day: %s, Hour: %s, Min: %s", day, hour, min);
    }

    public void subscribe(ITimeTracker subscriber) {
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(ITimeTracker subscriber) {
        this.subscribers.remove(subscriber);
    }
}
