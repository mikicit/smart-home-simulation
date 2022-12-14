package dev.mikita.sh.entity.sensor.factories;

import dev.mikita.sh.core.event.ILocation;
import dev.mikita.sh.entity.sensor.ASensor;

import java.util.List;

public interface SensorFactory {
    ASensor create(String type, ILocation location);
    List<ASensor> getSensors();
}
