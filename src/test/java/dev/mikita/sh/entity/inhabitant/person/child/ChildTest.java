package dev.mikita.sh.entity.inhabitant.person.child;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.PersonFactory;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildAwakeState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildSleepingState;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.builder.HouseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ChildTest {

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
                .addPerson("CHILD", "Kevin", PersonGender.MALE)
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
    void checkChildSleepingState() {
        AInhabitant child = PersonFactory.getInstance().getPersons().get(0);

        child.changeState(new ChildSleepingState(child));

        assertTrue((child.getState() instanceof ChildSleepingState));
    }

    @Test
    void checkChildAwakeState() {
        AInhabitant child = PersonFactory.getInstance().getPersons().get(0);

        child.changeState(new ChildAwakeState(child));

        assertTrue((child.getState() instanceof ChildAwakeState));
    }

    @Test
    void checkHungryEvent() throws IOException {
        AInhabitant child = PersonFactory.getInstance().getPersons().get(0);

        child.update(49 * 3600L * 1000000000L);

        assertTrue(((Child) child).getDispatchedHungerEvent());
    }

    @Test
    void checkPoopedEvent() throws IOException {
        AInhabitant child = PersonFactory.getInstance().getPersons().get(0);

        child.update(49 * 3600L * 1000000000L);

        assertTrue(((Child) child).getDispatchedPoopedEvent());
    }

}