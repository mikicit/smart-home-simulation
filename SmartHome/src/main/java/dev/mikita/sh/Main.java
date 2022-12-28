package dev.mikita.sh;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;

import java.io.IOException;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) throws Exception {
        // Logging Config
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logger/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e);
        }

        // house config
        HouseBuilder houseBuilder = new HouseBuilder();
        House house = houseBuilder
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
                            .addPerson("ADULT", "Mikita")
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
                            .addPerson("CHILD", "Jiri Sebek")
                            .addPerson("ADULT", "Roma")
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

        SHSystem system = SHSystem.getInstance();

        system.init(house);
        system.start(10000,9000000000000000000L);

//        Thread.sleep(1000);
//        system.stop();
    }
}
