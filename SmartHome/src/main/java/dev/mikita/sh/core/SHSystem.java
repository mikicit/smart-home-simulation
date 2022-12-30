package dev.mikita.sh.core;

import dev.mikita.sh.core.event.EventDispatcher;
import dev.mikita.sh.core.report.ReportSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.core.task.TaskSystem;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;

import java.io.IOException;

/**
 * Class representing the main system
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

    private SHSystem() {}

    /**
     * Returns system's instance
     * @return instance
     */
    public static SHSystem getInstance() {
        if (instance == null) {
            instance = new SHSystem();
        }
        return instance;
    }

    /**
     * Initializes the house and all needed systems
     */
    public void init() {
        this.simulation = new Simulation();
        this.eventDispatcher = new EventDispatcher();
        this.reportSystem = new ReportSystem();
        this.taskSystem = new TaskSystem();

        // house config
        HouseBuilder houseBuilder = new HouseBuilder();
        house = houseBuilder
                .addSensor("WIND")
                .addFloor(1)
                    .addRoom("Bedroom")
                        .addEntrance("DOOR", 2)
                        .addEntrance("WINDOW", 3)
                        .addSensor("HEAT")
                        .addSensor("LIGHT")
                        .addSensor("SMOKE")
                        .addDevice("TV", "Tv")
                        .addItem("GUITAR", "Guitar")
                        .addDevice("HEATER", "Heater")
                        .addPerson("ADULT", "Mikita", PersonGender.MALE)
                        .addPerson("ADULT", "Roma", PersonGender.MALE)
                        .addPet("DRAGON", "La la Dragon")
                        .end()
                    .addRoom("Kitchen")
                        .addEntrance("DOOR", 2)
                        .addEntrance("WINDOW", 3)
                        .addSensor("HEAT")
                        .addSensor("LIGHT")
                        .addSensor("SMOKE")
                        .addSensor("WATER")
                        .addDevice("HEATER", "Heater")
                        .addDevice("HEATER", "Heater")
                        .addDevice("TV", "Tv")
                        .addDevice("OVEN", "Oven")
                        .addDevice("MICROWAVE", "Microwave")
                        .addDevice("WASHING_MACHINE", "Washing machine")
                        .addDevice("FRIDGE", "Fridge")
                        .addPerson("CHILD", "Jiri Sebek", PersonGender.MALE)
                        .addPerson("ADULT", "Darina", PersonGender.FEMALE)
                        .end()
                    .addRoom("Garage")
                        .addEntrance("DOOR", 2)
                        .addEntrance("WINDOW", 3)
                        .addSensor("HEAT")
                        .addSensor("SMOKE")
                        .addItem("CAR", "Car")
                        .addItem("SKIS", "Skis")
                        .addItem("BIKE", "Bike")
                        .addDevice("HEATER", "Heater")
                        .end()
                    .end()
                .getResult();

        wasInitialized = true;
    }

    /**
     * Starts the simulation
     * @param speed simulation's speed
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
     * Stops the simulation
     */
    public void stop() {
        simulation.stop();
    }

    /**
     * Returns report system
     * @return report system
     */
    public ReportSystem getReportSystem() {
        return reportSystem;
    }

    /**
     * Returns event dispatcher
     * @return event dispatcher
     */
    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    /**
     * Returns simulation
     * @return simulation
     */
    public Simulation getSimulation() {
        return simulation;
    }

    /**
     * Returns task system
     * @return task system
     */
    public TaskSystem getTaskSystem() {
        return taskSystem;
    }

    /**
     * Returns house
     * @return house
     */
    public House getHouse() {
        return house;
    }
}
