package dev.mikita.sh.core;

import dev.mikita.sh.core.config.ConfigBuilder;
import dev.mikita.sh.core.event.EventDispatcher;
import dev.mikita.sh.core.report.ReportSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.core.task.TaskSystem;
import dev.mikita.sh.entity.location.House;

/**
 * Class representing the main system.
 */
public class SHSystem {
    // References
    private static SHSystem instance;
    private EventDispatcher eventDispatcher;
    private Simulation simulation;
    private ReportSystem reportSystem;
    private TaskSystem taskSystem;
    private House house;

    // State
    private boolean wasInitialized = false;

    private SHSystem() {
        this.simulation = new Simulation();
        this.eventDispatcher = new EventDispatcher();
        this.taskSystem = new TaskSystem();
    }

    /**
     * Returns system's instance.
     *
     * @return instance
     */
    public static SHSystem getInstance() {
        if (instance == null) {
            instance = new SHSystem();
        }
        return instance;
    }

    /**
     * Initializes the house and all needed systems.
     *
     * @param house the house
     */
    public void init(House house) {
        if (wasInitialized) return;

        this.reportSystem = new ReportSystem();
        this.house = house;

        wasInitialized = true;
    }

    public void init(String path) {
        if (wasInitialized) return;

        this.reportSystem = new ReportSystem();
        this.house = ConfigBuilder.build(path);

        wasInitialized = true;
    }

    /**
     * Starts the simulation.
     *
     * @param speed          simulation's speed
     * @param timeToSimulate time to simulate
     * @throws Exception system wasn't initialized
     */
    public void start(int speed, long timeToSimulate) throws Exception {
        if (!wasInitialized) {
            throw new Exception("The system must be initialized");
        }

        simulation.start(speed, timeToSimulate);
    }

    /**
     * Stops the simulation.
     */
    public void stop() {
        simulation.stop();
    }

    /**
     * Returns report system.
     *
     * @return report system
     */
    public ReportSystem getReportSystem() {
        return reportSystem;
    }

    /**
     * Returns event dispatcher.
     *
     * @return event dispatcher
     */
    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    /**
     * Returns simulation.
     *
     * @return simulation simulation
     */
    public Simulation getSimulation() {
        return simulation;
    }

    /**
     * Returns task system.
     *
     * @return task system
     */
    public TaskSystem getTaskSystem() {
        return taskSystem;
    }

    /**
     * Returns house.
     *
     * @return house
     */
    public House getHouse() {
        return house;
    }
}
