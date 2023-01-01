package dev.mikita.sh.entity.inhabitant.person.adult;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.PersonFactory;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.inhabitant.person.adult.state.*;
import dev.mikita.sh.entity.inhabitant.person.child.Child;
import dev.mikita.sh.entity.inhabitant.pet.APet;
import dev.mikita.sh.entity.inhabitant.pet.PetFactory;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;
import dev.mikita.sh.entity.sensor.ASensor;
import dev.mikita.sh.entity.sensor.SmokeSensor;
import dev.mikita.sh.entity.sensor.WaterSensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static dev.mikita.sh.entity.sensor.SmokeSensor.SmokeSensorState.NO_SMOKE;
import static dev.mikita.sh.entity.sensor.WaterSensor.WaterSensorState.NO_WATER;
import static org.junit.jupiter.api.Assertions.*;

public class AdultTest {

    SHSystem system = SHSystem.getInstance();

    @BeforeEach
    void setup() throws Exception {
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
                .addPet("DRAGON", "Oliver")
                .addPerson("CHILD", "Kevin", PersonGender.MALE)
                .addSensor("SMOKE")
                .addSensor("WATER")
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
    void checkAdultWaitingState() {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);

        person.changeState(new AdultWaitingState(person));

        assertTrue(person.getState() instanceof AdultWaitingState);
    }

    @Test
    void checkAdultSleepingState() throws IOException {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);
        person.changeState(new AdultWaitingState(person));

        system.getSimulation().setHour(2);
        person.update(2 * 3600L * 1000000000L);

        assertTrue(person.getState() instanceof AdultSleepingState);
    }

    @Test
    void checkFemaleAdultWorkingState() throws IOException {
        AInhabitant female = PersonFactory.getInstance().getPersons().get(1);

        system.getSimulation().setHour(12);
        female.update(12 * 3600L * 1000000000L);

        assertFalse(female.getState() instanceof AdultWorkingState);
    }

    @Test
    void checkAdultDeviceUsingState() {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        ((Adult) person).useObject(device);

        assertTrue(person.getState() instanceof AdultDeviceUsingState);
    }

    @Test
    void checkAdultDeviceFixingState() {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);
        ADevice device = system.getHouse().getFloors().get(0).getRooms().get(0).getDevices().get(0);

        device.toBreak();
        device.fix((Adult) person);

        assertTrue(person.getState() instanceof AdultDeviceFixingState);
    }

    @Test
    void checkMaleAdultWorkingState() throws IOException {
        AInhabitant male = PersonFactory.getInstance().getPersons().get(0);
        male.changeState(new AdultWaitingState(male));

        system.getSimulation().setHour(15);
        male.update(15 * 3600L * 1000000000L);

        assertTrue(male.getState() instanceof AdultWorkingState);
    }

    // Doesn't always pass, because we have a random chance that adult wouldn't like to feed the baby
    @Test
    void checkHandleHungryChildEvent() throws IOException {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);
        AInhabitant child = PersonFactory.getInstance().getPersons().get(2);

        child.update(48 * 3600L * 1000000000L);
        person.update(48 * 3600L * 1000000000L);

        assertFalse(((Child) child).getDispatchedHungerEvent());
    }

    // Doesn't always pass, because we have a random chance that adult wouldn't like to change baby's diaper
    @Test
    void checkHandlePoopedChildEvent() throws IOException {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);
        AInhabitant child = PersonFactory.getInstance().getPersons().get(2);

        child.update(48 * 3600L * 1000000000L);
        person.update(48 * 3600L * 1000000000L);

        assertFalse(((Child) child).getDispatchedPoopedEvent());
    }

    // Doesn't always pass, because we have a random chance that adult wouldn't like to feed the pet
    @Test
    void checkHandleHungryPetEvent() throws IOException {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);
        APet pet = PetFactory.getInstance().getPets().get(0);

        pet.update(26 * 3600L * 1000000000L);
        person.update(26 * 3600L * 1000000000L);

        assertFalse(((APet) pet).getDispatchedHungerEvent());
    }

    // Doesn't always pass, because we have a random chance that adult wouldn't like to play with pet
    @Test
    void checkHandleBoredPetEvent() throws IOException {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);
        APet pet = PetFactory.getInstance().getPets().get(0);

        pet.update(26 * 3600L * 1000000000L);
        person.update(26 * 3600L * 1000000000L);

        assertFalse(((APet) pet).getDispatchedPlayedEvent());
    }

    @Test
    void checkHandleSmokeInRoomEvent() throws IOException {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);
        ASensor sensor = system.getHouse().getFloors().get(0).getRooms().get(1).getSensors().get(0);

        sensor.update(192 * 3600L * 1000000000L);
        person.update(192 * 3600L * 1000000000L);

        assertEquals(NO_SMOKE, ((SmokeSensor) sensor).getState());
    }

    @Test
    void checkHandleWaterInRoomEvent() throws IOException {
        AInhabitant person = PersonFactory.getInstance().getPersons().get(0);
        ASensor sensor = system.getHouse().getFloors().get(0).getRooms().get(1).getSensors().get(1);

        sensor.update(192 * 3600L * 1000000000L);
        person.update(192 * 3600L * 1000000000L);

        assertEquals(NO_WATER, ((WaterSensor) sensor).getState());
    }

}