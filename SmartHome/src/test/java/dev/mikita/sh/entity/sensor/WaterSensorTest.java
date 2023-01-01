package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static dev.mikita.sh.entity.sensor.WaterSensor.WaterSensorState.WATER;
import static org.junit.jupiter.api.Assertions.*;

public class WaterSensorTest {

    SHSystem system = SHSystem.getInstance();

    @BeforeEach
    void setup() {
        HouseBuilder houseBuilder = new HouseBuilder();

        House house = houseBuilder
                .addFloor(1)
                .addRoom("Bedroom")
                .addEntrance("DOOR", 1)
                .addEntrance("WINDOW", 2)
                .addDevice("TV", "Tv")
                .addDevice("HEATER", "Heater")
                .addItem("GUITAR", "Guitar")
                .addItem("SKIS", "Skis")
//                .addPerson("ADULT", "Tom", PersonGender.MALE)
//                .addPerson("ADULT", "Kate", PersonGender.FEMALE)
                .end()
                .addRoom("Living room")
                .addSensor("WATER")
                .addDevice("HEATER", "Heater")
                .end()
                .end()
                .addFloor(2)
                .addRoom("Kitchen")
                .end()
                .end()
                .getResult();

        system.init(house);
    }

    @Test
    void checkSensorState() {
        ASensor sensor = system.getHouse().getFloors().get(0).getRooms().get(1).getSensors().get(0);
        sensor.resetState();

        sensor.switchState();

        assertEquals(WATER, ((WaterSensor) sensor).getState());
    }

    @Test
    void checkSensorWork() throws IOException {
        ASensor sensor = system.getHouse().getFloors().get(0).getRooms().get(1).getSensors().get(0);

        sensor.update(192 * 3600L * 1000000000L);

        assertEquals(WATER, ((WaterSensor) sensor).getState());
    }

}