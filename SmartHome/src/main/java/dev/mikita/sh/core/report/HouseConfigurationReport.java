package dev.mikita.sh.core.report;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.person.PersonFactory;
import dev.mikita.sh.entity.inhabitant.pet.APet;
import dev.mikita.sh.entity.inhabitant.pet.PetFactory;
import dev.mikita.sh.entity.location.Floor;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.location.Room;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating house configuration report.
 */
public class HouseConfigurationReport {
    private FileWriter houseConfReport = null;

    public HouseConfigurationReport() {
    }

    /**
     * Generates report.
     * @throws IOException writing to file is unsuccessful
     */
    public void generateReport() throws IOException {
        try {
            this.houseConfReport = new FileWriter("report/HouseConfigurationReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        House house = SHSystem.getInstance().getHouse();
        List<APerson> persons = new ArrayList<>(PersonFactory.getInstance().getPersons());
        List<APet> pets = new ArrayList<>(PetFactory.getInstance().getPets());
        int inhabitants = persons.size() + pets.size();

        houseConfReport.write("------------------------------------House------------------------------------\n");
        houseConfReport.write("House has " + house.getFloors().size() + " floor(s).\n");

        for (Floor floor : house.getFloors()) {
            houseConfReport.write("Floor " + "number " + floor.getLevel() + " has "
                + floor.getRooms().size() + " rooms: \n");
            for (Room room : floor.getRooms()) {
                houseConfReport.write(String.format("Room \"%s\" has %s device(s): \n",
                        room.getName(),
                        room.getDevices().size()));
                for (ADevice device : room.getDevices()) {
                    houseConfReport.write(device.getName() + "\n");
                }
            }
        }
        houseConfReport.write("---------------------------------Inhabitants---------------------------------\n");
        houseConfReport.write("There are " + inhabitants + " inhabitants living in the house: "
                + persons.size() + " people and " + pets.size() + " pet(s).\n");

        houseConfReport.write("People: \n");
        for (APerson person : persons) {
            houseConfReport.write(String.format("%s \"%s\" \n",
                    person.getClass().getSimpleName(),
                    person.getName()));
        }

        houseConfReport.write("Pets: \n");
        for (APet pet : pets) {
            houseConfReport.write(String.format("%s \"%s\" \n",
                    pet.getClass().getSimpleName(),
                    pet.getName()));
        }

        houseConfReport.flush();
        houseConfReport.close();
    }

}
