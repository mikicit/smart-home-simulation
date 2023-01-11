package dev.mikita.sh.core.simulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing time simulation.
 */
public class Simulation {
    private long time = 0;
    private long tempTime = 0;
    private int sec, min, hour = 0;
    private int day = 1;
    private final List<ITimeTracker> subscribers = new ArrayList<>();
    private boolean exit = false;

    /**
     * Updates time.
     * @param time time
     * @throws IOException writing to file is unsuccessful
     */
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

    /**
     * Starts the simulation.
     * @param speed simulation's speed
     * @param timeToSimulate time to simulate
     */
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

    /**
     * Stops the simulation.
     */
    public void stop() {
        exit = true;
    }

    /**
     * Returns time.
     * @return time
     */
    public long getTime() {
        return time;
    }

    /**
     * Returns actual minute.
     * @return minute
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns actual hour.
     * @return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Returns actual day.
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns formatted time for printing.
     * @return formatted time
     */
    public String getFormattedTime() {
        return String.format("Day: %s, Hour: %s, Min: %s", day, hour, min);
    }

    /**
     * Subscribes an object for time.
     * @param subscriber object to subscribe
     */
    public void subscribe(ITimeTracker subscriber) {
        this.subscribers.add(subscriber);
    }

    /**
     * Unsubscribes the object.
     * @param subscriber object to unsubscribe
     */
    public void unsubscribe(ITimeTracker subscriber) {
        this.subscribers.remove(subscriber);
    }

    // For tests
    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
