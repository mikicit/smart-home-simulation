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

    public void fixWaterLeak(Adult adult) {
        adult.fixWaterLeak(this);
    }

    public void putOutTheFire(Adult adult) {
        adult.putOutTheFire(this);
    }

    public void addInhabitant(AInhabitant inhabitant) {
        this.inhabitants.add(inhabitant);
    }

    public void addEntrance(AEntrance entrance) {
        this.entrances.add(entrance);
    }

    public void addDevice(ADevice device) {
        this.devices.add(device);
    }

    public void addItem(AItem item) {
        this.items.add(item);
    }

    public void addSensor(AInternalSensor sensor) {
        this.sensors.add(sensor);
    }

    public InnerAtmosphere getAtmosphere() {
        return this.atmosphere;
    }

    public String getName() {
        return name;
    }

    public List<ADevice> getDevices() {
        return devices;
    }

    public List<AItem> getItems() {
        return items;
    }

    public List<AInternalSensor> getSensors() {
        return sensors;
    }
}