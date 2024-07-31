package dev.mikita.sh.entity.location;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.entrance.AEntrance;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.item.AItem;
import dev.mikita.sh.entity.location.atmosphere.InnerAtmosphere;
import dev.mikita.sh.entity.sensor.AInternalSensor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class representing a room.
 */
public class Room implements ILocation {
    // Logger
    private static final Logger log = Logger.getLogger(Room.class.getName());

    private final String name;
    private final InnerAtmosphere atmosphere = new InnerAtmosphere();
    private final List<AInhabitant> inhabitants = new ArrayList<>();
    private final List<AEntrance> entrances = new ArrayList<>();
    private final List<ADevice> devices = new ArrayList<>();
    private final List<AItem> items = new ArrayList<>();
    private final List<AInternalSensor> sensors = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    /**
     * Fixes a water leak.
     * @param adult the adult
     */
    public void fixWaterLeak(Adult adult) {
        adult.fixWaterLeak(this);
    }

    /**
     * Puts out the fire.
     * @param adult the adult
     */
    public void putOutTheFire(Adult adult) {
        adult.putOutTheFire(this);
    }

    /**
     * Adds an inhabitant.
     * @param inhabitant the inhabitant
     */
    public void addInhabitant(AInhabitant inhabitant) {
        this.inhabitants.add(inhabitant);
    }

    /**
     * Adds an entrance.
     * @param entrance the entrance
     */
    public void addEntrance(AEntrance entrance) {
        this.entrances.add(entrance);
    }

    /**
     * Adds a device.
     * @param device the device
     */
    public void addDevice(ADevice device) {
        this.devices.add(device);
    }

    /**
     * Adds an item.
     * @param item the item
     */
    public void addItem(AItem item) {
        this.items.add(item);
    }

    /**
     * Adds an internal sensor.
     * @param sensor the sensor
     */
    public void addSensor(AInternalSensor sensor) {
        this.sensors.add(sensor);
    }

    /**
     * Returns an atmosphere inside the house.
     * @return atmosphere
     */
    public InnerAtmosphere getAtmosphere() {
        return this.atmosphere;
    }

    /**
     * Returns the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the devices.
     * @return devices
     */
    public List<ADevice> getDevices() {
        return devices;
    }

    /**
     * Returns the items.
     * @return items
     */
    public List<AItem> getItems() {
        return items;
    }

    /**
     * Returns the internal sensors.
     * @return sensors
     */
    public List<AInternalSensor> getSensors() {
        return sensors;
    }
}