package dev.mikita.sh.entity.location.builder;

import dev.mikita.sh.entity.location.Floor;
import dev.mikita.sh.entity.location.House;

/**
 * Class for building the floors.
 */
public class FloorBuilder {
    private final Floor floor;
    private final HouseBuilder houseBuilder;
    private final House house;

    public FloorBuilder(HouseBuilder houseBuilder, House house, int level) {
        this.houseBuilder = houseBuilder;
        this.house = house;
        this.floor = new Floor(level);

        house.addFloor(floor);
    }

    /**
     * Adds the room to the floor.
     * @param name the name
     * @return room builder
     */
    public RoomBuilder addRoom(String name) {
        return new RoomBuilder(this, floor, name);
    }

    /**
     * Ends a builder.
     * @return house builder
     */
    public HouseBuilder end() {
        return houseBuilder;
    }
}
