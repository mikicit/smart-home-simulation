package dev.mikita.sh;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
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

        SHSystem system = SHSystem.getInstance();

        // Config 1
//        HouseBuilder houseBuilderOne = new HouseBuilder();
//        House houseOne = houseBuilderOne
//            .addSensor("WIND")
//            .addFloor(1)
//                .addRoom("Bedroom")
//                    .addEntrance("DOOR", 2)
//                    .addEntrance("WINDOW", 3)
//                    .addSensor("HEAT")
//                    .addSensor("LIGHT")
//                    .addSensor("SMOKE")
//                    .addDevice("TV", "Tv")
//                    .addItem("GUITAR", "Guitar")
//                    .addDevice("HEATER", "Heater")
//                    .addPerson("ADULT", "Mikita", PersonGender.MALE)
//                    .addPerson("ADULT", "Roma", PersonGender.MALE)
//                    .addPet("DRAGON", "La la Dragon")
//                    .end()
//                .addRoom("Kitchen")
//                    .addEntrance("DOOR", 2)
//                    .addEntrance("WINDOW", 3)
//                    .addSensor("HEAT")
//                    .addSensor("LIGHT")
//                    .addSensor("SMOKE")
//                    .addSensor("WATER")
//                    .addDevice("HEATER", "Heater")
//                    .addDevice("HEATER", "Heater")
//                    .addDevice("TV", "Tv")
//                    .addDevice("OVEN", "Oven")
//                    .addDevice("MICROWAVE", "Microwave")
//                    .addDevice("WASHING_MACHINE", "Washing machine")
//                    .addDevice("FRIDGE", "Fridge")
//                    .addPerson("CHILD", "Jiri Sebek", PersonGender.MALE)
//                    .addPerson("ADULT", "Darina", PersonGender.FEMALE)
//                    .end()
//                .addRoom("Garage")
//                    .addEntrance("DOOR", 2)
//                    .addEntrance("WINDOW", 3)
//                    .addSensor("HEAT")
//                    .addSensor("SMOKE")
//                    .addItem("CAR", "Car")
//                    .addItem("SKIS", "Skis")
//                    .addItem("BIKE", "Bike")
//                    .addDevice("HEATER", "Heater")
//                    .end()
//                .end()
//            .getResult();

        // Config 2
        HouseBuilder houseBuilderTwo = new HouseBuilder();
        House houseTwo = houseBuilderTwo
            .addSensor("WIND")
            .addFloor(1)
                .addRoom("Living room")
                    .addEntrance("DOOR", 3)
                    .addEntrance("WINDOW", 3)
                    .addDevice("FRIDGE", "Fridge")
                    .addDevice("TV", "Tv")
                    .addDevice("LIGHT", "Lamp")
                    .addDevice("LIGHT", "Lamp 2")
                    .addDevice("AIR_CONDITIONER", "Air conditioner")
                    .addPerson("ADULT", "Mikita", PersonGender.MALE)
                    .addPet("DRAGON", "Azrail")
                    .end()
                .addRoom("Kitchen")
                    .addEntrance("DOOR", 2)
                    .addEntrance("WINDOW", 2)
                    .addSensor("HEAT")
                    .addSensor("LIGHT")
                    .addSensor("SMOKE")
                    .addSensor("WATER")
                    .addDevice("HEATER", "Heater")
                    .addDevice("LIGHT", "Lamp")
                    .addDevice("OVEN", "Oven")
                    .addDevice("MICROWAVE", "Microwave")
                    .addDevice("WASHING_MACHINE", "Washing machine")
                    .addDevice("FRIDGE", "Fridge")
                    .addPerson("ADULT", "Roma", PersonGender.MALE)
                    .end()
                .addRoom("Garage")
                    .addEntrance("DOOR", 2)
                    .addEntrance("WINDOW", 3)
                    .addSensor("HEAT")
                    .addSensor("SMOKE")
                    .addDevice("HEATER", "Heater")
                    .addDevice("LIGHT", "Lamp")
                    .addItem("CAR", "Car")
                    .addItem("SKIS", "Skis")
                    .addItem("BIKE", "Bike")
                    .end()
                .end()
            .addFloor(2)
                .addRoom("Bedroom")
                    .addEntrance("DOOR", 1)
                    .addEntrance("WINDOW", 2)
                    .addSensor("HEAT")
                    .addSensor("LIGHT")
                    .addSensor("SMOKE")
                    .addDevice("LIGHT", "Lamp")
                    .addDevice("HEATER", "Heater")
                    .addDevice("TV", "Tv")
                    .addDevice("AIR_CONDITIONER", "Air conditioner")
                    .addItem("GUITAR", "Guitar")
                    .addPerson("ADULT", "Nancy", PersonGender.FEMALE)
                    .addPerson("ADULT", "Darina", PersonGender.FEMALE)
                    .addPet("DRAGON", "Lilit")
                    .end()
                .addRoom("Bathroom")
                    .addEntrance("DOOR", 1)
                    .addSensor("HEAT")
                    .addSensor("LIGHT")
                    .addSensor("WATER")
                    .addDevice("LIGHT", "Lamp")
                    .addDevice("HEATER", "Heater")
                    .addDevice("WASHING_MACHINE", "Washing machine")
                    .end()
                .addRoom("Children's room")
                    .addEntrance("DOOR", 1)
                    .addEntrance("WINDOW", 2)
                    .addSensor("HEAT")
                    .addSensor("LIGHT")
                    .addSensor("SMOKE")
                    .addDevice("LIGHT", "Lamp")
                    .addDevice("HEATER", "Heater")
                    .addPerson("CHILD", "Lili", PersonGender.FEMALE)
                    .addPerson("CHILD", "Robert", PersonGender.MALE)
                    .addPet("DRAGON", "Norbert")
                    .end()
                .end()
            .getResult();

        system.init(houseTwo);
        system.start(10000,9000000000000000000L);

//        Thread.sleep(1000);
//        system.stop();
    }
}
