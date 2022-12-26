package dev.mikita.sh.core;

import dev.mikita.sh.core.event.EventDispatcher;
import dev.mikita.sh.core.report.ReportSystem;
import dev.mikita.sh.core.simulation.Simulation;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;

import java.io.IOException;

public class SHSystem {
    private static SHSystem instance;
    private EventDispatcher eventDispatcher;
    private Simulation simulation;
    private ReportSystem reportSystem;
    private House house;

    private SHSystem() {}

    public static SHSystem getInstance() {
        if (instance == null) {
            instance = new SHSystem();
        }
        return instance;
    }

    public void init(String config) throws IOException {}

    public void init() throws IOException {
        // Init Services
        eventDispatcher = new EventDispatcher();
        simulation = new Simulation();
        reportSystem = new ReportSystem();

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
                        .addItem("GUITAR", "Guitar")
                        .addDevice("HEATER", "Heater")
                        .addPerson("ADULT", "Mikita")
                        .addPerson("CHILD", "Jiri Sebek")
                        .end()
                    .addRoom("Kitchen")
                        .addEntrance("DOOR", 2)
                        .addEntrance("WINDOW", 3)
                        .addSensor("HEAT")
                        .addSensor("LIGHT")
                        .addDevice("HEATER", "Heater")
                        .addDevice("TV", "Tv")
                        .addDevice("WASHING_MACHINE", "Washing machine")
                        .addDevice("FRIDGE", "Fridge")
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
                        .addPet("DRAGON", "La la Dragon")
                        .end()
                    .end()
                .getResult();
    }

    public void start(int speed, long timeToSimulate) {
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
