package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.inhabitant.pet.state.PetAwakeState;
import dev.mikita.sh.entity.inhabitant.pet.state.PetSleepingState;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

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
                .addPet("DRAGON", "Oliver")
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
    void checkPetState() {
        APet pet = PetFactory.getInstance().getPets().get(0);

        pet.changeState(new PetSleepingState(pet));

        assertTrue(pet.getState() instanceof PetSleepingState);
    }

    @Test
    void checkPetAwakeState() {
        APet pet = PetFactory.getInstance().getPets().get(0);

        pet.changeState(new PetAwakeState(pet));

        assertTrue(pet.getState() instanceof PetAwakeState);
    }

    @Test
    void checkHungryEvent() throws IOException {
        APet pet = PetFactory.getInstance().getPets().get(0);

        pet.update(26 * 3600L * 1000000000L);

        assertTrue(pet.getDispatchedHungerEvent());
    }

    @Test
    void checkBoredEvent() throws IOException {
        APet pet = PetFactory.getInstance().getPets().get(0);

        pet.update(26 * 3600L * 1000000000L);

        assertTrue(pet.getDispatchedPlayedEvent());
    }
}