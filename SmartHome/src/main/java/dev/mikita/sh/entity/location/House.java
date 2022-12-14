package dev.mikita.sh.entity.location;

import dev.mikita.sh.core.event.ILocation;
import dev.mikita.sh.entity.item.AItem;
import dev.mikita.sh.entity.location.atmosphere.OuterAtmosphere;
import dev.mikita.sh.entity.sensor.AExternalSensor;

import java.util.ArrayList;
import java.util.List;

public class House implements ILocation {
    private final OuterAtmosphere atmosphere = new OuterAtmosphere();
    private final List<Floor> floors = new ArrayList<>();
    private final List<AExternalSensor> sensors = new ArrayList<>();

    public void addFloor(Floor floor) {
        this.floors.add(floor);
    }

    public void addSensor(AExternalSensor sensor) {
        this.sensors.add(sensor);
    }

    public OuterAtmosphere getAtmosphere() {
        return this.atmosphere;
    }
}