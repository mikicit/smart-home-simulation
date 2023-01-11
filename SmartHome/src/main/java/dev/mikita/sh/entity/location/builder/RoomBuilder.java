package dev.mikita.sh.entity.location.builder;

import dev.mikita.sh.entity.device.DeviceFactory;
import dev.mikita.sh.entity.entrance.EntranceFactory;
import dev.mikita.sh.entity.inhabitant.person.PersonFactory;
import dev.mikita.sh.entity.inhabitant.person.PersonGender;
import dev.mikita.sh.entity.inhabitant.pet.PetFactory;
import dev.mikita.sh.entity.item.ItemFactory;
import dev.mikita.sh.entity.location.Floor;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.entity.sensor.AInternalSensor;
import dev.mikita.sh.entity.sensor.factories.InternalSensorFactory;
import dev.mikita.sh.entity.sensor.factories.ISensorFactory;

/**
 * Class for building the room.
 */
public class RoomBuilder {
    private final Room room;
    private final FloorBuilder floorBuilder;
    private final Floor floor;

    public RoomBuilder(FloorBuilder floorBuilder, Floor floor, String name) {
        this.floorBuilder = floorBuilder;
        this.floor = floor;
        this.room = new Room(name);

        floor.addRoom(room);
    }

    /**
     * Adds a person.
     * @param type person's type (child, adult)
     * @param name the name
     * @param gender the gender
     * @return room builder
     */
    public RoomBuilder addPerson(String type, String name, PersonGender gender) {
        PersonFactory personFactory = PersonFactory.getInstance();

        room.addInhabitant(personFactory.create(type, name, gender, room));
        return this;
    }

    /**
     * Adds a pet.
     * @param type pet's type
     * @param name the name
     * @return room builder
     */
    public RoomBuilder addPet(String type, String name) {
        PetFactory petFactory = PetFactory.getInstance();

        room.addInhabitant(petFactory.create(type, name, room));
        return this;
    }

    /**
     * Adds an entrance.
     * @param type entrance's type (door, window)
     * @param quantity the quantity
     * @return room builder
     */
    public RoomBuilder addEntrance(String type, int quantity) {
        EntranceFactory entranceFactory = EntranceFactory.getInstance();

        for (int i = 0; i < quantity; i++) {
            room.addEntrance(entranceFactory.create(type, room));
        }

        return this;
    }

    /**
     * Adds a device.
     * @param type device's type
     * @param name the name
     * @return room builder
     */
    public RoomBuilder addDevice(String type, String name) {
        DeviceFactory deviceFactory = DeviceFactory.getInstance();

        room.addDevice(deviceFactory.create(type, room, name));
        return this;
    }

    /**
     * Adds an item.
     * @param type item's type
     * @param name the name
     * @return room builder
     */
    public RoomBuilder addItem(String type, String name) {
        ItemFactory itemFactory = ItemFactory.getInstance();

        room.addItem(itemFactory.create(type, room, name));
        return this;
    }

    /**
     * Adds a sensor.
     * @param type sensor's type
     * @return room builder
     */
    public RoomBuilder addSensor(String type) {
        ISensorFactory sensorFactory = InternalSensorFactory.getInstance();

        room.addSensor((AInternalSensor) sensorFactory.create(type, room));
        return this;
    }

    /**
     * Ends a builder.
     * @return floor builder
     */
    public FloorBuilder end() {
        return floorBuilder;
    }
}
