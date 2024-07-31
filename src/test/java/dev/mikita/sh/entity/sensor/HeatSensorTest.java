package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.atmosphere.AAtmosphere;
import dev.mikita.sh.entity.location.atmosphere.InnerAtmosphere;
import dev.mikita.sh.entity.location.builder.HouseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HeatSensorTest {

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
                .addSensor("HEAT")
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
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(1).getDevices().get(0);

        sensor.switchState();

        assertTrue(device.isOn());
    }

    @Test
    void checkSensorLowTemperature() throws IOException {
        ASensor sensor = system.getHouse().getFloors().get(0).getRooms().get(1).getSensors().get(0);
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(1).getDevices().get(0);

        sensor.update(3 * 3600L * 1000000000L);

        assertTrue(device.isOn());
    }

    @Test
    void checkSensorHighTemperature() {
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(1).getDevices().get(0);
        AAtmosphere atmosphere = new InnerAtmosphere();

        atmosphere.setTemperature(30);

        assertEquals("HeaterIdleState", device.getState().getClass().getSimpleName());
    }

}