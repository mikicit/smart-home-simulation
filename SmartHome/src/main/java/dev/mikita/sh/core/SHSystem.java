package dev.mikita.sh.core;

import dev.mikita.sh.core.event.EventDispatcher;
import dev.mikita.sh.core.report.ReportSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;

import java.io.IOException;

public class SHSystem {
    // References
    private static SHSystem instance;
    private EventDispatcher eventDispatcher;
    private Simulation simulation;
    private ReportSystem reportSystem;
    private House house;

    // State
    private boolean wasInitialized = false;

    private SHSystem() {}

    public static SHSystem getInstance() {
        if (instance == null) {
            instance = new SHSystem();
        }
        return instance;
    }

    public void init(House house) throws IOException {
        this.simulation = new Simulation();
        this.eventDispatcher = new EventDispatcher();
        this.reportSystem = new ReportSystem();
        this.house = house;

        wasInitialized = true;
    }

    public void start(int speed, long timeToSimulate) throws Exception {
        if (!wasInitialized) {
            throw new Exception("The system must be initialized");
        }

        simulation.start(speed, timeToSimulate);
    }

    public void stop() {
        simulation.stop();
    }

    public ReportSystem getReportSystem() {
        return reportSystem;
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public House getHouse() {
        return house;
    }
}
