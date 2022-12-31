package dev.mikita.sh.entity.location;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.location.builder.HouseBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseTest {

   @Test
    void checkHouseBuilder() {
       SHSystem system = SHSystem.getInstance();
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

       assertEquals(2, house.getFloors().size());
       assertEquals(2, house.getFloors().get(0).getRooms().size());
       assertEquals(2, house.getFloors().get(0).getRooms().get(0).getDevices().size());
       assertEquals(2, house.getFloors().get(0).getRooms().get(0).getItems().size());
   }
  
}