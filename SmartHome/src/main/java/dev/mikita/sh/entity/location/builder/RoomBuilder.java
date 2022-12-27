package dev.mikita.sh.entity.location.builder;

import dev.mikita.sh.entity.device.DeviceFactory;
import dev.mikita.sh.entity.entrance.EntranceFactory;
import dev.mikita.sh.entity.inhabitant.person.PersonFactory;
import dev.mikita.sh.entity.inhabitant.pet.PetFactory;
import dev.mikita.sh.entity.item.ItemFactory;
import dev.mikita.sh.entity.location.Floor;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.entity.sensor.AInternalSensor;
import dev.mikita.sh.entity.sensor.factories.InternalSensorFactory;
import dev.mikita.sh.entity.sensor.factories.ISensorFactory;

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

    public RoomBuilder addPerson(String type, String name) {
        PersonFactory personFactory = PersonFactory.getInstance();

        room.addInhabitant(personFactory.create(type, name, room));
        return this;
    }

    public RoomBuilder addPet(String type, String name) {
        PetFactory petFactory = PetFactory.getInstance();

        room.addInhabitant(petFactory.create(type, name, room));
        return this;
    }

    public RoomBuilder addEntrance(String type, int quantity) {
        EntranceFactory entranceFactory = EntranceFactory.getInstance();

        for (int i = 0; i < quantity; i++) {
            room.addEntrance(entranceFactory.create(type, room));
        }

        return this;
    }

    public RoomBuilder addDevice(String type, String name) {
        DeviceFactory deviceFactory = DeviceFactory.getInstance();

        room.addDevice(deviceFactory.create(type, room, name));
        return this;
    }

    public RoomBuilder addItem(String type, String name) {
        ItemFactory itemFactory = ItemFactory.getInstance();

        room.addItem(itemFactory.create(type, room, name));
        return this;
    }

    public RoomBuilder addSensor(String type) {
        ISensorFactory sensorFactory = InternalSensorFactory.getInstance();

        room.addSensor((AInternalSensor) sensorFactory.create(type, room));
        return this;
    }

    public FloorBuilder end() {
        return floorBuilder;
    }
}
