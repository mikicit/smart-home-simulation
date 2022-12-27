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

    private SHSystem() {
//        this.simulation = new Simulation();
//        this.eventDispatcher = new EventDispatcher();
//        this.reportSystem = new ReportSystem();
    }

    public static SHSystem getInstance() {
        if (instance == null) {
            instance = new SHSystem();
        }
        return instance;
    }

    public void init(String config) throws IOException {}

    public void init() throws IOException {
        this.simulation = new Simulation();
        this.eventDispatcher = new EventDispatcher();
        this.reportSystem = new ReportSystem();

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
                        .addDevice("TV", "Tv")
                        .addItem("GUITAR", "Guitar")
                        .addDevice("HEATER", "Heater")
                        .addPerson("ADULT", "Mikita")
                        .addPet("DRAGON", "La la Dragon")
                        .end()
                    .addRoom("Kitchen")
                        .addEntrance("DOOR", 2)
                        .addEntrance("WINDOW", 3)
                        .addSensor("HEAT")
                        .addSensor("LIGHT")
                        .addDevice("HEATER", "Heater")
                        .addDevice("HEATER", "Heater")
                        .addDevice("TV", "Tv")
                        .addDevice("OVEN", "Oven")
                        .addDevice("MICROWAVE", "Microwave")
                        .addDevice("WASHING_MACHINE", "Washing machine")
                        .addDevice("FRIDGE", "Fridge")
                        .addPerson("CHILD", "Jiri Sebek")
                        .addPerson("ADULT", "Roma")
                        .end()
                    .addRoom("Garage")
                        .addEntrance("DOOR", 2)
                        .addEntrance("WINDOW", 3)
                        .addSensor("HEAT")
                        .addItem("CAR", "Car")
                        .addItem("SKIS", "Skis")
                        .addItem("BIKE", "Bike")
                        .addDevice("HEATER", "Heater")
                        .end()
                    .end()
                .getResult();

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
