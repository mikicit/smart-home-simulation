package dev.mikita.sh.entity.device;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.heater.state.HeaterIdleState;
import dev.mikita.sh.entity.device.heater.state.HeaterOffState;
import dev.mikita.sh.entity.device.tv.state.TVBrokenState;
import dev.mikita.sh.entity.device.tv.state.TVIdleState;
import dev.mikita.sh.entity.device.tv.state.TVUsingState;
import dev.mikita.sh.entity.inhabitant.person.PersonFactory;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

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
                .addPerson("ADULT", "Tom", PersonGender.MALE)
                .addPerson("ADULT", "Kate", PersonGender.FEMALE)
                .end()
                .addRoom("Living room")
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
    void checkBrokenState() {
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        device.toBreak();

        assertTrue(device.getState() instanceof TVBrokenState);
        assertTrue(device.isBroken());
    }

    @Test
    void checkFixingState() {
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        device.toBreak();
        device.fix((Adult) PersonFactory.getInstance().getPersons().get(0));

        assertEquals("TVFixingState", device.getState().getClass().getSimpleName());
        assertTrue(device.isFixing());
    }

    @Test
    void checkCompleteFixing() {
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        device.toBreak();
        device.fix((Adult) PersonFactory.getInstance().getPersons().get(0));
        device.completeFixing((Adult) PersonFactory.getInstance().getPersons().get(0));

        assertTrue(device.getState() instanceof TVIdleState);
        assertFalse(device.isBroken());
    }

    @Test
    void checkUsingState() {
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        device.use(PersonFactory.getInstance().getPersons().get(1));

        assertTrue(device.getState() instanceof TVUsingState);
        assertTrue(device.isUsing());
    }

    @Test
    void checkOnState() {
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(1);

        device.on();

        assertTrue(device.getState() instanceof HeaterIdleState);
        assertTrue(device.isOn());
    }

    @Test
    void checkOffState() {
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(1);

        device.off();

        assertTrue(device.getState() instanceof HeaterOffState);
        assertTrue(device.isOff());
    }
}