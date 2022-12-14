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
        eventDispatcher = new EventDispatcher();
        timer = new SimulationTime();

        // house config
        HouseBuilder houseBuilder = new HouseBuilder();
        house = houseBuilder
                .addSensor("WIND")
                .addFloor(1)
                    .addRoom("Bedroom")
                        .addSensor("HEAT")
                        .addSensor("LIGHT")
                        .addEntrance("DOOR", 2)
                        .addEntrance("WINDOW", 3)
                        .addDevice("FRIDGE", "Fridge")
                        .addPerson("ADULT", "Mikita")
                        .addPerson("ADULT", "Darina")
                        .addDevice("HEATER", "Heater")
                        .end()
//                    .addRoom("Kitchen")
//                        .addSensor("HEAT")
//                        .addSensor("LIGHT")
//                        .addEntrance("DOOR", 2)
//                        .addEntrance("WINDOW", 3)
//                        .addPerson("CHILD", "Jiri")
//                        .addDevice("HEATER", "Heater")
//                        .end()
//                    .addRoom("Garage")
//                        .addEntrance("DOOR", 2)
//                        .addEntrance("WINDOW", 3)
//                        .addItem("CAR", "Car")
//                        .addItem("SKIS", "Skis")
//                        .addItem("BIKE", "Bike")
//                        .addDevice("HEATER", "Heater")
//                        .end()
                    .end()
                .getResult();

        long lastUpdate = System.nanoTime();
        int speed = 1000;

        while (true) {
            long currentTime = speed * (System.nanoTime());

            if (currentTime - lastUpdate > (1000000000 / 60)) {
                long delta = currentTime - lastUpdate;
                lastUpdate = currentTime;

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
}
