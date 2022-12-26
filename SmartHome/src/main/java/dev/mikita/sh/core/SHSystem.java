package dev.mikita.sh.core;

import dev.mikita.sh.core.event.EventDispatcher;
import dev.mikita.sh.core.report.ReportSystem;
import dev.mikita.sh.core.time.SimulationTime;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;

public class SHSystem {
    private static SHSystem instance;
    private EventDispatcher eventDispatcher;
    private SimulationTime timer;
    private ReportSystem reportSystem;
    private House house;

    private SHSystem() {}

    public static SHSystem getInstance() {
        if (instance == null) {
            instance = new SHSystem();
        }
        return instance;
    }

    public void init() {
        // Init Services
        eventDispatcher = new EventDispatcher();
        timer = new SimulationTime();
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

    public void start(long timeToSimulate) {
        long lastUpdate = System.nanoTime();
        int speed = 1000;

        while (timeToSimulate > 0) {
            long currentTime = System.nanoTime();

            if (currentTime - lastUpdate > (1000000000 / 60)) {
                long delta = speed * (currentTime - lastUpdate);
                lastUpdate = currentTime;
                timeToSimulate -= delta;

                timer.update(delta);
            }
        }
    }

    public ReportSystem getReportSystem() {
        return reportSystem;
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    public SimulationTime getTimer() {
        return timer;
    }

    public House getHouse() {
        return house;
    }
}
