package dev.mikita.sh.entity.location;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.entrance.AEntrance;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.item.AItem;
import dev.mikita.sh.entity.location.atmosphere.InnerAtmosphere;
import dev.mikita.sh.entity.sensor.AInternalSensor;

import java.util.ArrayList;
import java.util.List;

public class Room implements ILocation {
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
}